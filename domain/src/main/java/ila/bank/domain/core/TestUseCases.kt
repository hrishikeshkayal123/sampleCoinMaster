package ila.bank.domain.core


import ila.bank.data.core.ApiException
import ila.bank.data.core.ResultWrapper
import ila.bank.data.source.DataSourceMode
import ila.bank.data.source.network.retrofit.OkhttpConfiguration
import ila.bank.domain.di.DaggerDomainComponent
import ila.bank.domain.di.DomainComponent
import ila.bank.domain.usecases.AllCoinsUseCases
import ila.bank.domain.usecases.CoinDetailUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject



/**
 * Created a object class where this consumer will be inject the objects
 * */
abstract class TestUseCases {

    private val testCoroutineScope = CoroutineScope(Dispatchers.IO)

    abstract val baseUrl: String
    abstract val okhttpConfiguration: OkhttpConfiguration
    abstract val dataSourceMode: DataSourceMode

    /**
     * Consuming the object for unit testings
     * */
    @Inject
    lateinit var getAllCoinsUseCase: AllCoinsUseCases

    @Inject
    lateinit var getCoinsDetailUseCase: CoinDetailUseCases

    /**
     * checking the output condition from test cases
     * */
    abstract fun <T> checkUseCondition(
        message: String,
        resultWrapper: ResultWrapper<ApiException, T>,
        match: (ResultWrapper<ApiException, T>) -> Boolean
    )

    /**
     * creating a component and injection the objects in this class file
     * */
    open fun onCreate() {
        val domainComponent: DomainComponent = DaggerDomainComponent
            .factory().create(
                baseUrl = baseUrl,
                okhttpConfiguration = okhttpConfiguration,
                dataSourceMode = dataSourceMode
            )

        domainComponent.inject(this)
    }

    /**
     * execution of test cases with boilerplate function
     * */
    protected fun <T> executeUseCase(useCase: suspend () -> ResultWrapper<ApiException, T>):
            ResultWrapper<ApiException, T> =
        runBlocking {
            return@runBlocking withContext(testCoroutineScope.coroutineContext) {
                useCase.invoke()
            }
        }

    open fun onTerminate() {}

}