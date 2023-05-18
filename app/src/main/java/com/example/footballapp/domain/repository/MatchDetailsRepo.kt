package com.example.footballapp.domain.repository

import com.example.footballapp.data.models.MatchDetailDto

interface MatchDetailsRepo {

    suspend fun fetchMatchDetails(): MatchDetailDto
}