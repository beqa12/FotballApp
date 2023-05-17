package com.example.footballapp.data.network.service

import com.example.footballapp.data.models.MatchDetailDto
import com.example.footballapp.data.network.endpoints.Endpoints
import retrofit2.Response
import retrofit2.http.GET

interface FootballApi {

    @GET(Endpoints.MATCH_DETAILS)
    suspend fun fetchMathDetails():Response<MatchDetailDto>
}