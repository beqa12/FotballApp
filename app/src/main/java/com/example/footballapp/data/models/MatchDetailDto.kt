package com.example.footballapp.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchDetailDto(
    @SerializedName("resultCode")
    var resultCode: Int?,
    @SerializedName("match")
    var match: MatchDto
): Serializable

data class MatchDto(
    @SerializedName("team1")
    var team1: TeamInformationDto?,
    @SerializedName("team2")
    var team2: TeamInformationDto?,
    @SerializedName("matchTime")
    var matchTime: Double?,
    @SerializedName("matchDate")
    var matchDate: Long?,
    @SerializedName("stadiumAdress")
    var stadiumAdress: String?,
    @SerializedName("matchSummary")
    var matchSummary: MatchSummaryDto
): Serializable

data class TeamInformationDto(
    @SerializedName("teamName")
    var teamName: String?,
    @SerializedName("teamImage")
    var teamImage: String?,
    @SerializedName("score")
    var score: Int?,
    @SerializedName("ballPosition")
    var ballPosition: Int?
): Serializable

data class MatchSummaryDto(
    @SerializedName("summaries")
    var matchSummaries: List<MatchSummariesDto>?
): Serializable

data class MatchSummariesDto(
    @SerializedName("actionTime")
    var actionTime: String?,
    @SerializedName("team1Action")
    var team1Action: List<TeamActionDto>?,
    @SerializedName("team2Action")
    var team2Action: List<TeamActionDto>?
): Serializable

data class TeamActionDto(
    @SerializedName("actionType")
    var actionType: MatchActionType?,
    @SerializedName("teamType")
    var teamType: MatchTeamType?,
    @SerializedName("action")
    var action: ActionDto
): Serializable

data class ActionDto(
    @SerializedName("player")
    var player: PlayerDto?,
    @SerializedName("player1")
    var player1: PlayerDto?,
    @SerializedName("player2")
    var player2: PlayerDto?,
    @SerializedName("goalType")
    var goalType: GoalType?
): Serializable

data class PlayerDto(
    @SerializedName("playerName")
    var playerName: String?,
    @SerializedName("playerImage")
    var playerImage: String?
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

enum class MatchTeamType(teamType: Int){
    TEAM1  (1),
    TEAM2  (2)
}

