package com.terabyte.isitweatherinfo.navigation

import com.terabyte.core.util.DateHelper
import com.terabyte.domain.model.CityModel
import com.terabyte.domain.model.WeatherModel
import java.util.Calendar

sealed class ScreenState {
    data class ChooseCity(var cityName: String, var date: Calendar) : ScreenState() {
        companion object {
            fun getDefault(): ChooseCity {
                return ChooseCity("", DateHelper.getDefaultDate())
            }
        }
    }

    data class WeatherDetails(val cityModel: CityModel, val weatherModel: WeatherModel) :
        ScreenState()

    data object Loading : ScreenState()

    data object Error : ScreenState()
}