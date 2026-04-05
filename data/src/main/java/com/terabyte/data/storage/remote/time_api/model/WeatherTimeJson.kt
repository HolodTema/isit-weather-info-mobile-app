package com.terabyte.data.storage.remote.time_api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherTimeJson(
    @Json(name = "sunrise") val sunriseTime: String,
    @Json(name = "sunset") val sunsetTime: String,
    @Json(name = "day_length") val dayLen: String
)