package com.example.footballapp.data.repository

import com.example.footballapp.data.models.MatchDetailDto
import com.example.footballapp.data.network.base.BaseApiCallback
import com.example.footballapp.data.network.service.FootballApi

class FootballRepoImpl(private val api: FootballApi): BaseApiCallback() {

    suspend fun getInfo(): MatchDetailDto{
        return apiRequest { api.fetchMathDetails() }
    }
}