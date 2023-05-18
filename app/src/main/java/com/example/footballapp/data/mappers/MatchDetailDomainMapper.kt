package com.example.footballapp.data.mappers

import android.util.Log
import com.example.footballapp.data.models.MatchDetailDto
import com.example.footballapp.domain.models.*

class MatchDetailDomainMapper: BaseMapper<MatchDetailDto, MatchDetail> {

    override fun mapToDomainModel(dto: MatchDetailDto): MatchDetail {


        val test = ArrayList<Player>()

        val matchSummaries = ArrayList<MatchSummaries>()

        val teamsActions = ArrayList<TeamAction>()

        val playerActions = ArrayList<ActionTest>()


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

        dto.match.matchSummary?.matchSummaries?.forEach { matchSummaryDto ->
            val players = ArrayList<PlayerTest>()
            matchSummaryDto.team1Action?.forEach { actionDto ->
                actionDto?.action?.players?.forEach { playerDto ->
                    val player = PlayerTest(
                        playerName = playerDto?.playerName,
                        playerImage = playerDto?.playerImage,
                        goalType = actionDto?.action?.goalType,
                        teamType = actionDto?.teamType,
                        actionType = actionDto?.actionType
                    )
                    players.add(player)
                }
            }

            matchSummaryDto.team2Action?.forEach { actionDto ->
                actionDto?.action?.players?.forEach { playerDto ->
                    val player = PlayerTest(
                        playerName = playerDto?.playerName,
                        playerImage = playerDto?.playerImage,
                        goalType = actionDto?.action?.goalType,
                        teamType = actionDto?.teamType,
                        actionType = actionDto?.actionType
                    )
                    players.add(player)
                }
            }

            val actionTest = ActionTest(
                player = players,
                actionTime = matchSummaryDto.actionTime
            )
            playerActions.add(actionTest)
        }
//        playerActions.forEach {
//            Log.e("TAG", "Domain -> $it")
//        }

        return MatchDetail(
            resultCode = dto.resultCode,
            match = match,
            actions = playerActions
        )
    }
}