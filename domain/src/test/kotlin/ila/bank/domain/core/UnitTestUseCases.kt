package ila.bank.domain.core

import ila.bank.data.core.ApiException
import ila.bank.data.core.ResultWrapper
import ila.bank.data.source.DataSourceMode
import ila.bank.data.source.network.retrofit.OkhttpConfiguration
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before



/**
 * Creating a base class of unit test cases with flag of internet connection &
 * with dataSourceMode (ONLINE OR PROXY)
 * */
open class UnitTestUseCases constructor(
    isInternetOn: Boolean,
    override val dataSourceMode: DataSourceMode = DataSourceMode.ONLINE
) : TestUseCases() {


    override val baseUrl: String = "https://api.coingecko.com/"
    override val okhttpConfiguration: OkhttpConfiguration = TestOkhttpConfiguration(isInternetOn)

    @Before
    override fun onCreate() {
        super.onCreate()
    }

    override fun <T> checkUseCondition(
        message: String,
        resultWrapper: ResultWrapper<ApiException, T>,
        match: (ResultWrapper<ApiException, T>) -> Boolean
    ) {
        MatcherAssert.assertThat(
            resultWrapper,
            object : Matcher<ResultWrapper<ApiException, T>> {
                override fun describeTo(description: Description?) {
                    description?.appendText(message)
                }

                override fun matches(item: Any?): Boolean {

                    if (item is ResultWrapper<*, *>) {
                        val result = item as ResultWrapper<ApiException, T>
                        return match(result)

                    }

                    return false
                }

                override fun describeMismatch(item: Any?, mismatchDescription: Description?) {
                    mismatchDescription?.appendText(message)
                }

                @Deprecated("Deprecated in Java")
                override fun _dont_implement_Matcher___instead_extend_BaseMatcher_() {

                }

            })
    }

    @After
    override fun onTerminate() {
        super.onTerminate()
    }


}