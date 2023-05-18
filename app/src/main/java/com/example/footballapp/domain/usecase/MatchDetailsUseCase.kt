package com.example.footballapp.domain.usecase

import com.example.footballapp.data.mappers.MatchDetailDomainMapper
import com.example.footballapp.domain.models.MatchDetail
import com.example.footballapp.domain.repository.MatchDetailsRepo

class MatchDetailsUseCase(private val mapper: MatchDetailDomainMapper, private val repo: MatchDetailsRepo) {

    suspend fun execute(): MatchDetail {
        val dto = repo.fetchMatchDetails()
        return mapper.mapToDomainModel(dto)
    }
}