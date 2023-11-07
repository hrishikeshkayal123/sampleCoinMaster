package ila.bank.domain.di

import dagger.Module
import dagger.Provides
import ila.bank.data.di.DataModule
import ila.bank.data.repositories.CoinDataRepository
import ila.bank.data.source.DataSourceMode
import ila.bank.data.source.network.INetworkData
import ila.bank.data.source.proxy.ProxyDataSource
import javax.inject.Singleton



/**
 * Creating a module to initialize the necessary objects including data module
 * */
@Module(includes = [DataModule::class])
class DomainModule {

    /**
     * providing the coin repository with dependent objects from data module
     * */

    @Provides
    @Singleton
    fun provideCoinDataRepository(
        iNetworkData: INetworkData,
        proxyDataSource: ProxyDataSource,
        dataSourceMode: DataSourceMode
    ): CoinDataRepository {
        return CoinDataRepository(iNetworkData, proxyDataSource, dataSourceMode)
    }

}