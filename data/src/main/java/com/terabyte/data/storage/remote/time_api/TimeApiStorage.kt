package com.terabyte.data.storage.remote.time_api

import com.terabyte.data.storage.remote.time_api.model.WeatherTimeJson

interface TimeApiStorage {

    suspend fun getWeatherTime(
        latitude: Double,
        longitude: Double,
        date: String
    ): Result<WeatherTimeJson>

}