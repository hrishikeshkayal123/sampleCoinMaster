package ila.bank.domain.core

import ila.bank.data.source.network.retrofit.APIInterceptor
import okhttp3.Request



/**
 * Creating an interceptor for domain module with mock internet connection flag
 * */
class TestAPIInterceptors constructor(private val isInternetOn: Boolean) : APIInterceptor() {

    /**
     * mocking internet switch by flag isInternetOn
     * */
    override fun checkInternetIsAvailable(): Boolean = isInternetOn

    /***
     * mapping the request headers
     * */
    override fun onRequestCreated(requestBuilder: Request.Builder): Request {
        return requestBuilder.apply {
            header("Accept", "application/json")
            header("Content-Type", "application/json")
        }.build()
    }
}