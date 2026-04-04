package com.terabyte.domain.repository

import com.terabyte.domain.model.CityModel

interface CityRepository {

    suspend fun getCityCoordinates(name: String): CityModel

}