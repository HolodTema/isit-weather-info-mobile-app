package com.terabyte.data.repository

import com.terabyte.data.storage.remote.temperature_api.TemperatureApiStorage
import com.terabyte.data.storage.remote.time_api.TimeApiStorage
import com.terabyte.domain.model.CityModel
import com.terabyte.domain.model.WeatherModel
import com.terabyte.domain.repository.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val temperatureApiStorage: TemperatureApiStorage,
    private val timeApiStorage: TimeApiStorage
) : WeatherRepository {

    override suspend fun getWeatherDetails(cityModel: CityModel): Result<WeatherModel> {
        val resultTemperature =
            temperatureApiStorage.getTemperature(cityModel.latitude, cityModel.longitude)

        if (resultTemperature.isFailure) {
            return Result.failure(resultTemperature.exceptionOrNull() ?: Exception())
        }

        val weatherModel = WeatherModel(
            "sunrise",
            "sunset",
            "daylen",
            resultTemperature.getOrNull()?.dayTemp ?: "",
            resultTemperature.getOrNull()?.nightTemp ?: "",
        )
        return Result.success(weatherModel)
    }

}