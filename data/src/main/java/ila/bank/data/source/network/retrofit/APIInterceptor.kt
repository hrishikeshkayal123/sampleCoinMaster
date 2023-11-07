package ila.bank.data.source.network.retrofit

import ila.bank.data.core.ApiException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException



/**
 * Adding an interceptor to customise the logic of api request and responses
 * also centralized the error response state
 * */
abstract class APIInterceptor : Interceptor {

    /**
     * checking a network availability from device logic
     * */
    abstract fun checkInternetIsAvailable(): Boolean

    open fun onRequestCreated(requestBuilder: Request.Builder): Request {
        return requestBuilder.build()
    }

    open fun onResponseData(response: Response): Response {
        return response
    }

    @kotlin.jvm.Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val ioException = IOException()
        /**
         * checking a network availability from device logic
         * */
        if (!checkInternetIsAvailable()) {
            throw ioException.initCause(ApiException.NoInternetConnection)
        }
        /**
         * optional update the request before network call
         * eg: applying  headers, encryption logic, cross checking ssl handshake, etc
         * */
        val request = onRequestCreated(chain.request().newBuilder())

        val response = chain.proceed(request)
        if (response.isSuccessful) {
            /**
             * optional update the response after success
             * eg: decryption logic, cross checking ssl handshake, etc
             * */
            return onResponseData(response)
        }

        /**
         * mapping new api exception based on response code
         * and throws back from retrofit IO thread
         * */
        val apiException = when (val resCode = response.code) {
            -1 -> ApiException.NoInternetConnection
            500 -> ApiException.InternalServerException
            400, 404 -> {
                val errorRes = response.body?.string()
                ApiException.BadRequestException(errorRes)
            }
            else -> {
                ApiException.UnknownException(resCode, response.message)
            }
        }

        throw ioException.initCause(apiException)
    }


}