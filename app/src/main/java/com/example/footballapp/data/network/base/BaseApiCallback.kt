package com.example.footballapp.data.network.base

import com.example.footballapp.utils.ApiExceptions
import retrofit2.Response

abstract class BaseApiCallback {

    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>): T{
        val response = call.invoke()
        var error = ""
        if (response.isSuccessful){
            return response.body()!!
        } else {
            error = when {
                response.code() == 500 -> {
                    "SERVER_ERROR"
                }
                else -> {
                    "UNKNOWN_ERROR" + response.code()
                }
            }
        }
        throw ApiExceptions(error)
    }
}