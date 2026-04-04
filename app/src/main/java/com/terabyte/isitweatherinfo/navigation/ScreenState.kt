package com.terabyte.isitweatherinfo.navigation

import com.terabyte.domain.model.CityModel
import com.terabyte.domain.model.WeatherModel

sealed class ScreenState {
    data class ChooseCity(var cityName: String) : ScreenState()

    data class WeatherDetails(val cityModel: CityModel, val weatherModel: WeatherModel) :
        ScreenState()

    data object Loading : ScreenState()

    data object Error : ScreenState()
}