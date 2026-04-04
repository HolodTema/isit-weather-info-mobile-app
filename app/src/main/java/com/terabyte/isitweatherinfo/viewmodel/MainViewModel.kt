package com.terabyte.isitweatherinfo.viewmodel

import androidx.lifecycle.ViewModel
import com.terabyte.domain.usecase.GetCityCoordinatesByNameUseCase
import com.terabyte.isitweatherinfo.navigation.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCityCoordinatesByNameUseCase: GetCityCoordinatesByNameUseCase
) : ViewModel() {

    private val _stateFlowScreenState = MutableStateFlow<ScreenState>(ScreenState.ChooseCity(""))
    val stateFlowScreenState = _stateFlowScreenState.asStateFlow()

    init {

    }

}