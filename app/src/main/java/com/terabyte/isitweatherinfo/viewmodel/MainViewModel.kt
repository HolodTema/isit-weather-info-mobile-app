package com.terabyte.isitweatherinfo.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terabyte.domain.model.WeatherModel
import com.terabyte.domain.usecase.GetCityCoordinatesByNameUseCase
import com.terabyte.domain.usecase.GetWeatherDetailsUseCase
import com.terabyte.isitweatherinfo.navigation.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCityCoordinatesByNameUseCase: GetCityCoordinatesByNameUseCase,
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase
) : ViewModel() {

    private val _stateFlowScreenState = MutableStateFlow<ScreenState>(ScreenState.ChooseCity.getDefault())
    val stateFlowScreenState = _stateFlowScreenState.asStateFlow()

    fun loadWeatherDetails(cityName: String) {
        val screenState = stateFlowScreenState.value
        if (screenState !is ScreenState.ChooseCity) {
            return
        }

        _stateFlowScreenState.value = ScreenState.Loading
        getCityCoordinatesByNameUseCase(cityName, screenState.date) { cityModel ->
            viewModelScope.launch(Dispatchers.Main) {
                if (cityModel == null) {
                    _stateFlowScreenState.value = ScreenState.Error
                }
                else {
                    withContext(Dispatchers.IO) {
                        val resultWeatherModel = getWeatherDetailsUseCase(cityModel)

                        withContext(Dispatchers.Main) {
                            resultWeatherModel.onFailure {
                                _stateFlowScreenState.value = ScreenState.Error
                            }.onSuccess { weatherModel ->
                                _stateFlowScreenState.value = ScreenState.WeatherDetails(cityModel, weatherModel)
                            }
                        }
                    }
                }
            }
        }
    }

    fun navigateToChooseCityScreen() {
        _stateFlowScreenState.value = ScreenState.ChooseCity.getDefault()
    }

    fun saveChooseCityScreenState(cityName: String) {
        val screenState = _stateFlowScreenState.value
        if (screenState is ScreenState.ChooseCity) {
            screenState.cityName = cityName
        }
    }

    fun updateChooseCityDate(newDate: Calendar) {
        val screenState = _stateFlowScreenState.value
        if (screenState is ScreenState.ChooseCity) {
            screenState.date = newDate
        }
    }
}