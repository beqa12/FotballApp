package com.example.footballapp.di

import com.example.footballapp.ui.details.viewmodel.MatchDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewmodeModule = module {

    viewModel {
        MatchDetailViewModel(get())
    }
}