package ila.bank.carousel_demo.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import ila.bank.data.source.network.retrofit.APIInterceptor
import javax.inject.Inject


/**
 * Creating an retrofit interceptor from app-level to provide actual internet connection
 */
class AppAPIInterceptor @Inject constructor(private val application: Application) :
    APIInterceptor() {

    override fun checkInternetIsAvailable(): Boolean {
        return checkInternet()
    }

    private fun checkInternet(): Boolean {
        var result = false
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        return result
    }
}