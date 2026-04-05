package com.terabyte.domain.model

data class CityModel(
    val name: String,
    //date in format yyyy-mm-dd
    val date: String,
    val latitude: Double,
    val longitude: Double
)

