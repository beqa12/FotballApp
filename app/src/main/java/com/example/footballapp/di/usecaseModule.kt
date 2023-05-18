package com.example.footballapp.di

import com.example.footballapp.domain.usecase.MatchDetailsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module


val useCaseModule = module {
    factory {
        MatchDetailsUseCase(get(), get())
    }
}