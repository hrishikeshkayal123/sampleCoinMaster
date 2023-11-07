package ila.bank.carousel_demo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ila.bank.carousel_demo.BuildConfig
import ila.bank.carousel_demo.utils.AppOkhttpConfig
import ila.bank.data.di.DataModule
import ila.bank.data.source.DataSourceMode
import ila.bank.data.source.network.retrofit.OkhttpConfiguration
import javax.inject.Named
import javax.inject.Singleton



/**
 * Creating a data module objects to initialize from app-level
 * */
@Module(includes = [DataModule::class])
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideAPIUrl(): String {
        return BuildConfig.SERVER_URL
    }

    @Provides
    @Singleton
    fun provideOkhttpConfiguration(appOkhttpConfig: AppOkhttpConfig): OkhttpConfiguration {
        return appOkhttpConfig
    }

    @Provides
    @Singleton
    fun provideDataSourceMode(): DataSourceMode {
        return if (BuildConfig.FLAVOR.equals(
                "proxy",
                true
            )
        ) DataSourceMode.PROXY else DataSourceMode.ONLINE
    }

}