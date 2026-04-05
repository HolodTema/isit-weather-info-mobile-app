package com.terabyte.isitweatherinfo.navigation

import com.terabyte.domain.model.CityModel
import com.terabyte.domain.model.WeatherModel
import java.util.Calendar

sealed class ScreenState {
    data class ChooseCity(var cityName: String, var date: Calendar) : ScreenState() {
        companion object {
            fun getDefault(): ChooseCity {
                val date = Calendar.getInstance().apply {
                    set(2026, Calendar.JANUARY, 1)
                }
                return ChooseCity("", date)
            }
        }
    }

    data class WeatherDetails(val cityModel: CityModel, val weatherModel: WeatherModel) :
        ScreenState()

    data object Loading : ScreenState()

    data object Error : ScreenState()
}