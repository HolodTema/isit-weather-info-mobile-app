package com.terabyte.data.storage.remote.temperature_api

import com.terabyte.data.storage.remote.temperature_api.model.WeatherTemperatureModel
import com.terabyte.data.storage.remote.temperature_api.service.TemperatureService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemperatureApiStorageImpl @Inject constructor(
    private val temperatureService: TemperatureService
) : TemperatureApiStorage {

    override suspend fun getTemperature(
        latitude: Double,
        longitude: Double
    ): Result<WeatherTemperatureModel> {
        val response =  temperatureService.getWeatherTemperature(latitude, longitude)
        val weatherTemperatureModel = response.body()
            ?: return Result.failure(Exception("Temperature API request error. Status code=${response.code()}"))
        return Result.success(weatherTemperatureModel)
    }
}