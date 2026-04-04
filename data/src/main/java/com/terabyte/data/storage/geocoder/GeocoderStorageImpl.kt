package com.terabyte.data.storage.geocoder

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import com.terabyte.domain.di.qualifier.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeocoderStorageImpl @Inject constructor(@ApplicationContext private val context: Context) :
    GeocoderStorage {

    override fun getCoordinatesByCityName(cityName: String, onResultListener: (Pair<Double, Double>?) -> Unit) {
        val geocoder = Geocoder(context)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocationName(cityName, 1, object: Geocoder.GeocodeListener {
                    override fun onError(errorMessage: String?) {
                        onResultListener(null)
                    }

                    override fun onGeocode(addresses: List<Address?>) {
                        if (addresses.isEmpty()) {
                            onResultListener(null)
                            return
                        }
                        val address = addresses[0]
                        if (address == null) {
                            onResultListener(null)
                        }
                        else {
                            onResultListener(address.latitude to address.longitude)
                        }
                    }
                })
            }
            else {
                val addresses = geocoder.getFromLocationName(cityName, 1)
                val address = addresses?.get(0)
                if (address == null) {
                    onResultListener(null)
                }
                else {
                    onResultListener(address.latitude to address.longitude)
                }
            }
        }
        catch (e: IOException) {
            e.printStackTrace()
            onResultListener(null)
        }
    }
}