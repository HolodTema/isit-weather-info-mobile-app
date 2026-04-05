package com.terabyte.data.storage.remote.temperature_api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherTemperatureResponseJson(
    val daily: WeatherTemperatureJson
)