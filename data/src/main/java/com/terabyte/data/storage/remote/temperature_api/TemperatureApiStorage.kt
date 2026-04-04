package com.terabyte.data.storage.remote.temperature_api

import com.terabyte.data.storage.remote.temperature_api.model.WeatherTemperatureModel

interface TemperatureApiStorage {

    suspend fun getTemperature(latitude: Double, longitude: Double): Result<WeatherTemperatureModel>

}
