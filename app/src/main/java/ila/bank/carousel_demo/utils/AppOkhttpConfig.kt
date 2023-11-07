package ila.bank.carousel_demo.utils

import android.app.Application
import ila.bank.data.source.network.retrofit.APIInterceptor
import ila.bank.data.source.network.retrofit.OkhttpConfiguration
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Creating an okhttp Config from app-level also we can perform ssl pining on okhttp client
 */
@Singleton
class AppOkhttpConfig @Inject constructor(private val application: Application) :
    OkhttpConfiguration() {
    override fun getAPIInterceptor(): APIInterceptor {
        return AppAPIInterceptor(application)
    }

    override fun configOkhttpClient(builder: okhttp3.OkHttpClient.Builder): okhttp3.OkHttpClient.Builder {
        return builder
    }
}