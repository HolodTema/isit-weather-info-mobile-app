package com.terabyte.domain.model

import java.util.Calendar

data class CityModel(
    val name: String,
    val date: Calendar,
    val latitude: Double,
    val longitude: Double
)

