package com.terabyte.data.storage.remote.temperature_api

import com.terabyte.data.storage.remote.temperature_api.model.WeatherTemperatureJson

interface TemperatureApiStorage {

    suspend fun getTemperature(latitude: Double, longitude: Double): Result<WeatherTemperatureJson>

}
