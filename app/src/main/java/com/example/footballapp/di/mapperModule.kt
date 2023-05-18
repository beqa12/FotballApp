package com.example.footballapp.di

import com.example.footballapp.data.mappers.MatchDetailDomainMapper
import org.koin.dsl.module


val mapperModule = module {

    factory {
        MatchDetailDomainMapper()
    }
}