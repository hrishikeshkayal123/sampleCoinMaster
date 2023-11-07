package ila.bank.data.source.network.retrofit

import okhttp3.OkHttpClient



/**
 * customizing the okhttp object from domian/presentation layer
 */
abstract class OkhttpConfiguration {
    /**
     * this fun is used applying api interceptor to retrofit instance
     */
    abstract fun getAPIInterceptor(): APIInterceptor
    /**
     * this fun is used config okhttp client for connection time out ssl pinnning
     */
    abstract fun configOkhttpClient(builder: OkHttpClient.Builder): OkHttpClient.Builder


}