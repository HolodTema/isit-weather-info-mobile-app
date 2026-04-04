package com.terabyte.data.storage.geocoder

interface GeocoderStorage {

    fun getCoordinatesByCityName(
        cityName: String,
        onResultListener: (Pair<Double, Double>?) -> Unit
    )
}