package com.terabyte.data.di.module

import android.content.Context
import android.location.Geocoder
import com.terabyte.data.storage.geocoder.GeocoderStorage
import com.terabyte.data.storage.geocoder.GeocoderStorageImpl
import com.terabyte.domain.di.qualifier.ApplicationContext
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface GeocoderModule {

    @Binds
    @Singleton
    abstract fun bindGeocoderStorage(storage: GeocoderStorageImpl): GeocoderStorage

    companion object {

        @Provides
        @Singleton
        fun provideGeocoder(@ApplicationContext context: Context): Geocoder {
            return Geocoder(context)
        }
    }
}