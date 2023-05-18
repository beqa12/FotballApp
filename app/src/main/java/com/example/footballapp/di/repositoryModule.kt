package com.example.footballapp.di

import com.example.footballapp.data.repository.MatchDetailRepoImpl
import com.example.footballapp.domain.repository.MatchDetailsRepo
import org.koin.dsl.module


var repoModule = module {

    single<MatchDetailsRepo> {
        MatchDetailRepoImpl(get())
    }
}