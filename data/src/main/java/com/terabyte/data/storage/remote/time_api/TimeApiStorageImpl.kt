package com.terabyte.data.storage.remote.time_api

import com.terabyte.data.storage.remote.time_api.model.WeatherTimeJson
import com.terabyte.data.storage.remote.time_api.service.TimeService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TimeApiStorageImpl @Inject constructor(
    private val timeService: TimeService
) : TimeApiStorage {

    override suspend fun getWeatherTime(latitude: Double, longitude: Double): Result<WeatherTimeJson> {
        try {
            val weatherTimeJson = timeService.getWeatherTime(latitude, longitude).results
            return Result.success(weatherTimeJson)
        } catch (e: HttpException) {
            e.printStackTrace()
            return Result.failure(Exception("HTTP ${e.code()}: ${e.message}"))
        } catch (e: IOException) {
            e.printStackTrace()
            return Result.failure(Exception("Network error: ${e.message}"))
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure(Exception("Error: ${e.message}"))
        }
    }

}