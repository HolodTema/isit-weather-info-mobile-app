package com.terabyte.isitweatherinfo.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terabyte.domain.model.WeatherModel
import com.terabyte.domain.usecase.GetCityCoordinatesByNameUseCase
import com.terabyte.isitweatherinfo.navigation.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCityCoordinatesByNameUseCase: GetCityCoordinatesByNameUseCase
) : ViewModel() {

    private val _stateFlowScreenState = MutableStateFlow<ScreenState>(ScreenState.ChooseCity(""))
    val stateFlowScreenState = _stateFlowScreenState.asStateFlow()

    fun loadWeatherDetails(cityName: String) {
        _stateFlowScreenState.value = ScreenState.Loading
        getCityCoordinatesByNameUseCase(cityName) { cityModel ->
            viewModelScope.launch(Dispatchers.Main) {
                if (cityModel == null) {
                    _stateFlowScreenState.value = ScreenState.Error
                }
                else {
                    val weatherModel = WeatherModel(
                        sunriseTime = "sunrise",
                        sunsetTime = "sunset",
                        dayLen = "daylen",
                        dayTemp = "daytemp",
                        nightTemp = "nightTemp"
                    )
                    _stateFlowScreenState.value = ScreenState.WeatherDetails(cityModel, weatherModel)
                }
            }
        }
    }

    fun navigateToChooseCityScreen() {
        _stateFlowScreenState.value = ScreenState.ChooseCity("")
    }

    fun saveChooseCityScreenState(cityName: String) {
        val screenState = _stateFlowScreenState.value
        if (screenState is ScreenState.ChooseCity) {
            screenState.cityName = cityName
        }
    }

}