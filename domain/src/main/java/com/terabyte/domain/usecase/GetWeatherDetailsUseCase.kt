package com.terabyte.domain.usecase

import com.terabyte.domain.model.CityModel
import com.terabyte.domain.model.WeatherModel
import com.terabyte.domain.repository.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetWeatherDetailsUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(cityModel: CityModel): Result<WeatherModel> {
        return weatherRepository.getWeatherDetails(cityModel)
    }

}