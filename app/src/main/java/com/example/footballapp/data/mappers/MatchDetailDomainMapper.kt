package com.example.footballapp.data.mappers

import com.example.footballapp.data.models.MatchDetailDto
import com.example.footballapp.domain.models.*

class MatchDetailDomainMapper: BaseMapper<MatchDetailDto, MatchDetail> {

    var firstTeamHalfTimeResult = 0
    var secondTeamHalfTimeResult = 0

    override fun mapToDomainModel(dto: MatchDetailDto): MatchDetail {

        val playerActions = ArrayList<Action>()

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
            val players = ArrayList<Player>()
            matchSummaryDto.team1Action?.forEach { actionDto ->
                getHalfTimeResult(actionDto.action?.goalType,matchSummaryDto.actionTime,MatchTeamType.TEAM1())
                actionDto.action?.players?.forEach { playerDto ->
                    val player = Player(
                        playerName = playerDto?.playerName,
                        playerImage = playerDto?.playerImage,
                        goalType = actionDto.action?.goalType,
                        teamType = actionDto.teamType,
                        actionType = actionDto.actionType
                    )
                    players.add(player)
                }
            }

            matchSummaryDto.team2Action?.forEach { actionDto ->
                getHalfTimeResult(actionDto.action?.goalType, matchSummaryDto.actionTime, MatchTeamType.TEAM2())
                actionDto.action?.players?.forEach { playerDto ->
                    val player = Player(
                        playerName = playerDto?.playerName,
                        playerImage = playerDto?.playerImage,
                        goalType = actionDto.action?.goalType,
                        teamType = actionDto.teamType,
                        actionType = actionDto.actionType
                    )
                    players.add(player)
                }
            }
            var isBothTeam = false
            if (matchSummaryDto.team1Action != null && matchSummaryDto.team2Action != null){
                isBothTeam = true
            }

            val action = Action(
                player = players,
                actionTime = matchSummaryDto.actionTime,
                isBothTeam = isBothTeam
            )
            playerActions.add(action)
        }
        return MatchDetail(
            resultCode = dto.resultCode,
            match = match,
            actions = playerActions,
            firstTeamHalfTimeResult = firstTeamHalfTimeResult,
            secondTeamHalfTimeResult = secondTeamHalfTimeResult
        )
    }

    private fun getHalfTimeResult(goalType: Int?, actionTime: String?, teamType: MatchTeamType){
        goalType?.let {
            when(it){
                GoalType.GOAL().goalType -> {
                    when(teamType){
                        MatchTeamType.TEAM1() -> {
                            if (isFirstHalf(actionTime))
                                firstTeamHalfTimeResult ++
                            else
                                println("Not in the first half")
                        }
                        MatchTeamType.TEAM2() -> {
                            if (isFirstHalf(actionTime))
                                secondTeamHalfTimeResult ++
                            else
                                println("Not in the first half")
                        }
                        else -> {}
                    }
                }
                GoalType.OWN_GOAL().goalType -> {
                    when(teamType){
                        MatchTeamType.TEAM1() -> {
                           if (isFirstHalf(actionTime))
                               secondTeamHalfTimeResult ++
                            else
                                println("Not in the first half")

                        }
                        MatchTeamType.TEAM2() -> {
                            if (isFirstHalf(actionTime))
                                firstTeamHalfTimeResult ++
                            else
                                println("Not in the first half")
                        }
                        else -> {}
                    }
                }
                else -> {}
            }
        }
    }

    private fun isFirstHalf(actionTime: String?): Boolean{
        actionTime?.let {acTime ->
            return acTime.toInt() < 46
        }
        return false
    }
}