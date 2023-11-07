package ila.bank.domain.di

import dagger.BindsInstance
import dagger.Component
import ila.bank.data.di.DataModule
import ila.bank.data.source.DataSourceMode
import ila.bank.data.source.network.retrofit.OkhttpConfiguration
import ila.bank.domain.core.TestUseCases
import ila.bank.domain.usecases.AllCoinsUseCases
import ila.bank.domain.usecases.CoinDetailUseCases
import javax.inject.Named
import javax.inject.Singleton


/**
 * Creating a Component where it consumes the modules of domain
 * * */
@Singleton
@Component(modules = [DomainModule::class])
interface DomainComponent {


    @Singleton
    fun getAllCoinUseCase(): AllCoinsUseCases

    @Singleton
    fun getCoinDetailUseCase(): CoinDetailUseCases

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance dataSourceMode: DataSourceMode,
            @Named("baseUrl") @BindsInstance baseUrl: String,
            @BindsInstance okhttpConfiguration: OkhttpConfiguration,
        ): DomainComponent
    }

    @Singleton
    fun inject(testUseCases: TestUseCases)

}
