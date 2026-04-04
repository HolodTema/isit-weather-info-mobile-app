package com.terabyte.data.di.module

import com.terabyte.data.repository.CityRepositoryImpl
import com.terabyte.domain.repository.CityRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCityRepository(repository: CityRepositoryImpl): CityRepository
}