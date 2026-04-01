package com.terabyte.domain.repository

interface CityRepository {

    suspend fun getCityCoordinates(name: String)

}