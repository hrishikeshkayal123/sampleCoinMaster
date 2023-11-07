package ila.bank.domain.core

import ila.bank.data.source.network.retrofit.APIInterceptor
import ila.bank.data.source.network.retrofit.OkhttpConfiguration
import okhttp3.OkHttpClient



/**
 * Creating an okhttp Config for domain module
 * */
class TestOkhttpConfiguration constructor(private val isInternetOn: Boolean) :
    OkhttpConfiguration() {

    /**
     * passing our test interceptor with internet connection flag
     * */
    override fun getAPIInterceptor(): APIInterceptor {
        return TestAPIInterceptors(isInternetOn)
    }

    /**
     * we can build the okhttp object
     * */
    override fun configOkhttpClient(builder: OkHttpClient.Builder): OkHttpClient.Builder = builder
}