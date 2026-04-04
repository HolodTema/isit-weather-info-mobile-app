package com.terabyte.data.storage.remote.temperature_api.service

import com.terabyte.data.storage.remote.temperature_api.model.WeatherTemperatureModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TemperatureService {

    @GET("/v1/archive")
    suspend fun getWeatherTemperature(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("start_date") startDate: String = "2025-12-31",
        @Query("end_date") endDate: String = "2025-12-31",
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min",
        @Query("timezone") timezone: String = "auto"
    ): Response<WeatherTemperatureModel>

}