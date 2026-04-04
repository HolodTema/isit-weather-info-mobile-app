package com.terabyte.domain.repository

import com.terabyte.domain.model.CityModel
import com.terabyte.domain.model.WeatherModel

interface WeatherRepository {

    suspend fun getWeatherDetails(cityModel: CityModel): Result<WeatherModel>

}