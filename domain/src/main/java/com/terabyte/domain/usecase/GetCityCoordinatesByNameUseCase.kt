package com.terabyte.domain.usecase

import com.terabyte.domain.model.CityModel
import com.terabyte.domain.repository.CityRepository
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCityCoordinatesByNameUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    operator fun invoke(name: String, date: Calendar, onResultListener: (CityModel?) -> Unit) {
        cityRepository.getCityCoordinates(name, date, onResultListener)
    }

}