package com.example.footballapp.data.models

import com.example.footballapp.ui.details.custom.CustomActionDeserializer
import com.google.gson.annotations.JsonAdapter
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
    var matchSummary: MatchSummaryDto?
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
    var team1Action: List<TeamActionDto?>,
    @SerializedName("team2Action")
    var team2Action: List<TeamActionDto?>
): Serializable

data class TeamActionDto(
    @SerializedName("actionType")
    var actionType: Int?,
    @SerializedName("teamType")
    var teamType: Int?,
    @SerializedName("action")
    @JsonAdapter(CustomActionDeserializer::class)
    var action: ActionDto?
): Serializable

data class ActionDto(
    var players: List<PlayerDto?>,
    @SerializedName("goalType")
    var goalType: Int?
): Serializable

data class PlayerDto(
    @SerializedName("playerName")
    var playerName: String?,
    @SerializedName("playerImage")
    var playerImage: String?
): Serializable


