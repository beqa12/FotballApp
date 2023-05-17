package com.example.footballapp.di

import com.example.footballapp.data.network.interceptors.NoConnectionInterceptor
import com.example.footballapp.data.network.retrofit.FootballRetrofit
import com.example.footballapp.data.network.service.FootballApi
import org.koin.dsl.module


val networkModule = module {

    single {
        NoConnectionInterceptor(get())
    }

    single {
        FootballRetrofit(get())
    }

    single {
        return@single get<FootballRetrofit>().getRetrofitInstance().create(FootballApi::class.java)
    }


}