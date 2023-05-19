package com.example.footballapp.data.network

import com.example.footballapp.data.models.ActionDto
import com.example.footballapp.data.models.PlayerDto
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CustomActionDeserializer :
    JsonDeserializer<ActionDto> {

    private val GOAL_TYPE_KEY = "goalType"
    private val PLAYER_KEY = "player"

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ActionDto {

        val players = mutableListOf<PlayerDto?>()
        var goalType: Int? = null
        json?.asJsonObject?.entrySet()?.forEach { entry ->
            if(entry.key == GOAL_TYPE_KEY) goalType = entry.value.toString().toIntOrNull()
            else if(entry.key.startsWith(PLAYER_KEY)) players.add(context?.deserialize(entry.value, PlayerDto::class.java))
            else {
                println("Unknown Key")
            }
        }
        return ActionDto(
            goalType = goalType,
            players = players
        )
    }
}