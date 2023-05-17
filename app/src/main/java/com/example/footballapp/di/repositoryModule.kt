package com.example.footballapp.di

import com.example.footballapp.data.repository.FootballRepoImpl
import org.koin.dsl.module


var repoModule = module {
    single {
        FootballRepoImpl(get())
    }
}