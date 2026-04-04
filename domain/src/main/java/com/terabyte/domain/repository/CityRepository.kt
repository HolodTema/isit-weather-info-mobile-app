package com.terabyte.domain.repository

import com.terabyte.domain.model.CityModel

interface CityRepository {

    fun getCityCoordinates(name: String, onResultListener: (CityModel?) -> Unit)

}