package com.example.footballapp.domain.models

import java.io.Serializable

data class MatchDetail(
    var resultCode: Int?,
    var match: Match,
    var actions: List<ActionTest>
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

data class Action(
    var player: List<Player?>,
    var goalType: Int?
): Serializable

data class Player(
    var playerName: String?,
    var playerImage: String?,
): Serializable

data class PlayerTest(
    var playerName: String?,
    var playerImage: String?,
    var goalType: Int?,
    var teamType: Int?,
    var actionType: Int?
): Serializable



data class ActionTest(
    var player: List<PlayerTest?>,
    var actionTime: String?
): Serializable

enum class MatchActionType(actionType: Int){
    GOAL (1),
    YELLOW_CARD (2),
    RED_CARD (3),
    SUBSTITUTION (4)
}

enum class GoalType(goalType: Int){
    GOAL(1),
    OWN_GOAL(2)
}

sealed class MatchTeamType(){
    data class TEAM1(val teamType: Int = 1): MatchTeamType()
    data class TEAM2(val teamType: Int = 2): MatchTeamType()
}