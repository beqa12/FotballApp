package com.example.footballapp.domain.resource

sealed class Status<out T>{

    data class SUCCESS<out T>(val data: T, val message: String) : Status<T>()
    data class ERROR(val errorCode: Int) : Status<Nothing>()
    object NO_INTERNET : Status<Nothing>()
}
