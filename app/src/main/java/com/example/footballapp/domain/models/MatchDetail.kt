package com.example.footballapp.domain.models

import java.io.Serializable

data class MatchDetail(
    var resultCode: Int?,
    var match: Match,
    var actions: List<Action>,
    var firstTeamHalfTimeResult: Int = 0,
    var secondTeamHalfTimeResult: Int = 0
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
    var actionType: Int?,
    var teamType: Int?,
    var action: Action?
): Serializable

//data class Action(
//    var player: List<Player?>,
//    var goalType: Int?
//): Serializable

//data class Player(
//    var playerName: String?,
//    var playerImage: String?,
//): Serializable

data class Player(
    var playerName: String?,
    var playerImage: String?,
    var goalType: Int?,
    var teamType: Int?,
    var actionType: Int?
): Serializable



data class Action(
    var player: List<Player?>,
    var actionTime: String?,
    var isBothTeam: Boolean = false
): Serializable

sealed class MatchActionType{
    data class GOAL(val actionType: Int = 1): MatchActionType()
    data class YELLOW_CARD(val actionType: Int = 2): MatchActionType()
    data class RED_CARD(val actionType: Int = 3): MatchActionType()
    data class SUBSTITUTION(val actionType: Int = 4): MatchActionType()
}

sealed class GoalType {
    data class GOAL(val goalType: Int = 1) : GoalType()
    data class OWN_GOAL(val goalType: Int = 2) : GoalType()

}

sealed class MatchTeamType{
    data class TEAM1(val teamType: Int = 1): MatchTeamType()
    data class TEAM2(val teamType: Int = 2): MatchTeamType()
}