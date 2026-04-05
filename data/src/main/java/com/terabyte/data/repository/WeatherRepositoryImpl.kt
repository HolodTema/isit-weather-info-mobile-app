package com.terabyte.data.repository

import com.terabyte.data.storage.remote.temperature_api.TemperatureApiStorage
import com.terabyte.data.storage.remote.temperature_api.model.WeatherTemperatureJson
import com.terabyte.data.storage.remote.time_api.TimeApiStorage
import com.terabyte.data.storage.remote.time_api.model.WeatherTimeJson
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

        val resultTime = timeApiStorage.getWeatherTime(cityModel.latitude, cityModel.longitude)
        if (resultTime.isFailure) {
            return Result.failure(resultTime.exceptionOrNull() ?: Exception())
        }

        val weatherTimeJson = resultTime.getOrThrow()
        val weatherTemperatureJson = resultTemperature.getOrThrow()

        return Result.success(mapToWeatherModel(weatherTimeJson, weatherTemperatureJson))
    }

    private fun mapToWeatherModel(
        weatherTimeJson: WeatherTimeJson,
        weatherTemperatureJson: WeatherTemperatureJson
    ): WeatherModel {
        return WeatherModel(
            sunriseTime = weatherTimeJson.sunriseTime,
            sunsetTime = weatherTimeJson.sunsetTime,
            dayLen = weatherTimeJson.dayLen,
            dayTemp = weatherTemperatureJson.dayTemp[0],
            nightTemp = weatherTemperatureJson.nightTemp[0],
        )
    }
}