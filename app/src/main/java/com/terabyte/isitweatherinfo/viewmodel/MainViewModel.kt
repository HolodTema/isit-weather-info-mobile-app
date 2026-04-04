package com.terabyte.isitweatherinfo.viewmodel

import androidx.lifecycle.ViewModel
import com.terabyte.domain.usecase.GetCityCoordinatesByNameUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCityCoordinatesByNameUseCase: GetCityCoordinatesByNameUseCase
) : ViewModel() {

    init {

    }

}