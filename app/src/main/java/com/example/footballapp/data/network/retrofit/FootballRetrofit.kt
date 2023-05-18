package com.example.footballapp.data.network.retrofit

import com.example.footballapp.data.models.ActionDto
import com.example.footballapp.data.network.endpoints.Endpoints
import com.example.footballapp.data.network.interceptors.NoConnectionInterceptor
import com.example.footballapp.ui.details.custom.CustomActionDeserializer
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FootballRetrofit(private val noConnectionInterceptor: NoConnectionInterceptor) {

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val gson = GsonBuilder()
        .registerTypeAdapter(ActionDto::class.java, CustomActionDeserializer())
        .create()

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(noConnectionInterceptor)
        addInterceptor(httpLoggingInterceptor)
    }.build()

    private var retrofitInstance: Retrofit? = null

    fun getRetrofitInstance(): Retrofit {
        synchronized(this) {
            if (retrofitInstance == null) {
                retrofitInstance = Retrofit.Builder()
                    .baseUrl(Endpoints.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()
            }
            return retrofitInstance!!
        }
    }
}