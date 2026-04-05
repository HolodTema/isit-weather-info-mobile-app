package com.terabyte.domain.repository

import com.terabyte.domain.model.CityModel
import java.util.Calendar

interface CityRepository {

    fun getCityCoordinates(name: String, date: Calendar, onResultListener: (CityModel?) -> Unit)

}