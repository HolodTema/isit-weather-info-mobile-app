package com.terabyte.data.di.module

import com.terabyte.data.repository.CityRepositoryImpl
import com.terabyte.data.repository.WeatherRepositoryImpl
import com.terabyte.domain.repository.CityRepository
import com.terabyte.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCityRepository(repository: CityRepositoryImpl): CityRepository

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository
}