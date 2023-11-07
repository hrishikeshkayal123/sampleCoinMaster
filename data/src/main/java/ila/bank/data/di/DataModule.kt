package ila.bank.data.di

import dagger.Module
import dagger.Provides
import ila.bank.data.source.network.INetworkData
import ila.bank.data.source.network.retrofit.APIDataSource
import ila.bank.data.source.network.retrofit.OkhttpConfiguration
import ila.bank.data.source.proxy.ProxyDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton



/**
 * Creating a module to initialize the necessary objects
 * */
@Module
class DataModule {


    /**
     * providing the proxy data source object for mock api data
     * */

    @Provides
    @Singleton
    fun provideProxyData(): ProxyDataSource {
        return ProxyDataSource()
    }


    /**
     * providing the network data source object for remote api data
     * */

    @Provides
    @Singleton
    fun provideNetworkData(
        @Named("baseUrl") baseUrl: String,
        okHttpClient: OkHttpClient
    ): INetworkData {
        return APIDataSource(baseUrl, okHttpClient)
    }

    /**
     * providing the okhttp object for applying client object inside
     * retrofit
     * */

    @Provides
    @Singleton
    fun provideOkhttpClient(
        okhttpConfiguration: OkhttpConfiguration
    ): OkHttpClient {
        val httpLogging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val builder = OkHttpClient()
            .newBuilder()
        builder.addInterceptor(httpLogging)
        builder.addInterceptor(okhttpConfiguration.getAPIInterceptor())
        return okhttpConfiguration.configOkhttpClient(builder = builder)
            .build()
    }


}