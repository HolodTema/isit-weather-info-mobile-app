package com.terabyte.data.storage.remote.temperature_api

import com.terabyte.data.storage.remote.temperature_api.model.WeatherTemperatureJson
import com.terabyte.data.storage.remote.temperature_api.service.TemperatureService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemperatureApiStorageImpl @Inject constructor(
    private val temperatureService: TemperatureService
) : TemperatureApiStorage {

    override suspend fun getTemperature(
        latitude: Double,
        longitude: Double
    ): Result<WeatherTemperatureJson> {
        try {
            val weatherTemperatureJson =
                temperatureService.getWeatherTemperature(latitude, longitude).daily

            if (weatherTemperatureJson.dayTemp.isEmpty() || weatherTemperatureJson.nightTemp.isEmpty()) {
                return Result.failure(Exception("Error: request is successful, but json fields are empty."))
            }
            return Result.success(weatherTemperatureJson)

        } catch (e: HttpException) {
            return Result.failure(Exception("HTTP ${e.code()}: ${e.message}"))
        } catch (e: IOException) {
            return Result.failure(Exception("Network error: ${e.message}"))
        } catch (e: Exception) {
            return Result.failure(Exception("Error: ${e.message}"))
        }
    }
}