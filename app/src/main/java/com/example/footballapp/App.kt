package com.example.footballapp

import android.app.Application
import com.example.footballapp.di.networkModule
import com.example.footballapp.di.repoModule
import com.example.footballapp.di.viewmodeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, viewmodeModule, repoModule))
        }
    }
}