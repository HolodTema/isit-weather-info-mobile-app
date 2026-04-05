package com.terabyte.data.repository

import com.terabyte.data.storage.geocoder.GeocoderStorage
import com.terabyte.domain.model.CityModel
import com.terabyte.domain.repository.CityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryImpl @Inject constructor(private val geocoderStorage: GeocoderStorage) :
    CityRepository {

    override fun getCityCoordinates(name: String, onResultListener: (CityModel?) -> Unit) {
        geocoderStorage.getCoordinatesByCityName(name) { pairCoordinates ->
            if (pairCoordinates == null) {
                onResultListener(null)
            } else {
                onResultListener(
                    CityModel(name, "2026-01-01", pairCoordinates.first, pairCoordinates.second)
                )
            }
        }
    }

}