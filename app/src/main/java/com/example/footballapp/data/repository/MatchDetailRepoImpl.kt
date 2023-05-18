package com.example.footballapp.data.repository

import com.example.footballapp.data.models.MatchDetailDto
import com.example.footballapp.data.network.base.BaseApiCallback
import com.example.footballapp.data.network.service.FootballApi
import com.example.footballapp.domain.repository.MatchDetailsRepo

class MatchDetailRepoImpl(private val api: FootballApi): BaseApiCallback(), MatchDetailsRepo {


    override suspend fun fetchMatchDetails(): MatchDetailDto {
        return apiRequest { api.fetchMathDetails() }
    }
}