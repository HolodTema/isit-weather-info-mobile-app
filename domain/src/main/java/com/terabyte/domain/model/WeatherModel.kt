package com.terabyte.domain.model

data class WeatherModel(
    val sunriseTime: String,
    val sunsetTime: String,
    val dayLen: String,
    val dayTemp: Double,
    val nightTemp: Double
)

