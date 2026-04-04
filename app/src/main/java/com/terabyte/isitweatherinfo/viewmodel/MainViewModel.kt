package com.terabyte.isitweatherinfo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                    //load weather details via usecase
                }
            }
        }
    }

}