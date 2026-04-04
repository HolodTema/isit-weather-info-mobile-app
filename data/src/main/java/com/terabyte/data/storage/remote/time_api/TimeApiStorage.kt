package com.terabyte.data.storage.remote.time_api

interface TimeApiStorage {

    suspend fun getWeatherTime(): WeatherTimeModel

}