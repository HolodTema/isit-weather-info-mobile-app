package com.terabyte.data.storage.remote.time_api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherTimeResponseJson(
    val results: WeatherTimeJson
)
