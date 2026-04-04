package com.terabyte.data.storage.remote.temperature_api

import com.terabyte.data.di.qualifier.TemperatureApiRetrofit
import com.terabyte.data.storage.remote.temperature_api.model.WeatherTemperatureModel
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemperatureApiStorageImpl @Inject constructor(
    @TemperatureApiRetrofit private val retrofit: Retrofit
) : TemperatureApiStorage {

    override suspend fun getTemperature(
        latitude: Double,
        longitude: Double
    ): WeatherTemperatureModel {

    }
}