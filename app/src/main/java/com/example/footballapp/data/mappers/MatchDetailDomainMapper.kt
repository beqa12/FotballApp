package com.example.footballapp.data.mappers

import com.example.footballapp.data.models.MatchDetailDto
import com.example.footballapp.domain.models.Match
import com.example.footballapp.domain.models.MatchDetail
import com.example.footballapp.domain.models.MatchSummary
import com.example.footballapp.domain.models.TeamInformation

class MatchDetailDomainMapper: BaseMapper<MatchDetailDto, MatchDetail> {

    override fun mapToDomainModel(dto: MatchDetailDto): MatchDetail {
        val team1Information = TeamInformation(
            teamName = dto.match.team1?.teamName,
            teamImage = dto.match.team1?.teamImage,
            score = dto.match.team1?.score,
            ballPosition = dto.match.team1?.ballPosition
        )

        val team2Information = TeamInformation(
            teamName = dto.match.team2?.teamName,
            teamImage = dto.match.team2?.teamImage,
            score = dto.match.team2?.score,
            ballPosition = dto.match.team2?.ballPosition
        )
        val match = Match(
            team1 = team1Information,
            team2 = team2Information,
            matchTime = dto.match.matchTime,
            matchDate = dto.match.matchDate,
            stadiumAdress = dto.match.stadiumAdress,
            matchSummary = null
        )




        return MatchDetail(
            resultCode = dto.resultCode,
            match = match
        )
    }
}