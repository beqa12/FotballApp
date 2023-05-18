package com.example.footballapp.domain.models

import com.example.footballapp.data.models.*
import java.io.Serializable

data class MatchDetail(
    var resultCode: Int?,
    var match: Match
)

data class Match(
    var team1: TeamInformation?,
    var team2: TeamInformation?,
    var matchTime: Double?,
    var matchDate: Long?,
    var stadiumAdress: String?,
    var matchSummary: MatchSummary?
): Serializable


data class TeamInformation(
    var teamName: String?,
    var teamImage: String?,
    var score: Int?,
    var ballPosition: Int?
): Serializable

data class MatchSummary(
    var matchSummaries: List<MatchSummaries?>
): Serializable

data class MatchSummaries(
    var actionTime: String?,
    var team1Action: List<TeamAction>?,
    var team2Action: List<TeamAction>?
): Serializable

data class TeamAction(
    var actionType: MatchActionType?,
    var teamType: MatchTeamType?,
    var action: Action
): Serializable

data class Action(
    var player: Player?,
    var player1: Player?,
    var player2: Player?,
    var goalType: GoalType?
): Serializable

data class Player(
    var playerName: String?,
    var playerImage: String?
): Serializable
