package com.example.footballapp.data.network.retrofit

import com.example.footballapp.data.network.endpoints.Endpoints
import com.example.footballapp.data.network.interceptors.NoConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FootballRetrofit(private val noConnectionInterceptor: NoConnectionInterceptor) {

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(noConnectionInterceptor)
//        addInterceptor(environmentInterceptor)
//        addInterceptor(defaultParameterInterceptor)
        connectTimeout(1, TimeUnit.MINUTES) // connect timeout
        writeTimeout(1, TimeUnit.MINUTES) // write timeout
        readTimeout(1, TimeUnit.MINUTES)
        addInterceptor(httpLoggingInterceptor)
    }.build()

    private var retrofitInstance: Retrofit? = null

    fun getRetrofitInstance(): Retrofit {
        synchronized(this) {
            if (retrofitInstance == null) {
                retrofitInstance = Retrofit.Builder()
                    .baseUrl(Endpoints.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofitInstance!!
        }
    }
}