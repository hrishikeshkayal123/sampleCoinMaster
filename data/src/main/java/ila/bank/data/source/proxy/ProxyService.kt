package ila.bank.data.source.proxy

import com.google.gson.Gson
import kotlinx.coroutines.delay



abstract class ProxyService<Req, Res> {

    private val gson by lazy { Gson() }

    private val TAG = "ProxyService"

    /**
     * Execute the proxy logic for all apis
     * */
    abstract suspend fun execute(request: Req): Res

    /**
     * extendable logger function
     */

    open fun logger(message: String?) {
        println(message)
    }

    /**
     * mapping specific proxy api response
     */
    abstract fun mapProxyResponse(): Res

    /**
     * core logic of proceeding proxy call with logger & dummy delay time
     * */
    protected suspend fun Req.proceed(delayTime: Long = 1500L): Res {
        logger("$TAG : ${ProxyService::class.simpleName}")
        logger("Request : ${gson.toJson(this)}")
        delay(delayTime)
        val response = mapProxyResponse()
        logger("$TAG : ${ProxyService::class.simpleName}")
        logger("Response : ${gson.toJson(response)}")
        return response
    }


}