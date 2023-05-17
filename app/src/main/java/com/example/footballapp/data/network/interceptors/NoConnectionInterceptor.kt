package com.example.footballapp.data.network.interceptors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.footballapp.R
import com.example.footballapp.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NoConnectionInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()){
            throw NoInternetException(context.getString(R.string.internet_problem))
        }else {
            return chain.proceed(chain.request())
        }
    }

    private fun isNetworkAvailable(): Boolean{
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val capabilities =
            connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
        connectivityManager?.let {
            capabilities?.let {networkCapabilities ->
                result = when {
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        true
                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        true
                    }else -> {
                        false
                    }
                }
            }
        }
        return result
    }
}