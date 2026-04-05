package com.terabyte.data.storage.remote.time_api.service

import com.terabyte.data.storage.remote.time_api.model.WeatherTimeResponseJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeService {

    @GET("/json")
    suspend fun getWeatherTime(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("date") date: String = "2025-12-31"
    ): WeatherTimeResponseJson
}