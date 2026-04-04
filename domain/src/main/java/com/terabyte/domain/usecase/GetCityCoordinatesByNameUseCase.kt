package com.terabyte.domain.usecase

import com.terabyte.domain.model.CityModel
import com.terabyte.domain.repository.CityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCityCoordinatesByNameUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    operator fun invoke(name: String, onResultListener: (CityModel?) -> Unit) {
        cityRepository.getCityCoordinates(name, onResultListener)
    }

}