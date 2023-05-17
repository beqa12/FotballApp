package com.example.footballapp.data.mappers

interface BaseMapper<T, Domain> {

    fun mapToDomainModel(dto: T): Domain
}