package com.terabyte.data.storage.remote.temperature_api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherTemperatureJson(
    @Json(name = "temperature_2m_max") val dayTemp: List<Double>,
    @Json(name = "temperature_2m_min") val nightTemp: List<Double>
)