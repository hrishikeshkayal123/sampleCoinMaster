package ila.bank.data.di

import dagger.BindsInstance
import dagger.Component
import ila.bank.data.source.DataSourceMode
import ila.bank.data.source.network.retrofit.OkhttpConfiguration
import javax.inject.Named
import javax.inject.Singleton




/**
 * Creating a Component where it consumes the modules of data
 * * */

@Singleton
@Component(modules = [DataModule::class])
interface DataComponent {

    /**
     * Creating a factory of data component with params object to build
     * dependent objects from data module
     * */

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance dataSourceMode: DataSourceMode,
            @BindsInstance @Named("baseUrl") baseUrl: String,
            @BindsInstance okhttpConfiguration: OkhttpConfiguration,
        ): DataComponent
    }


}