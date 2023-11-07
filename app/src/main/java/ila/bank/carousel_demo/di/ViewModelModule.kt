package ila.bank.carousel_demo.di

import android.os.Handler
import android.os.Looper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent




/**
 * Creating a VM Component for VM-level instances
 * * */
@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideHandler(): Handler = Handler(Looper.getMainLooper())


}