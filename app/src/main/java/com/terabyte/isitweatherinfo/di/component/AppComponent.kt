package com.terabyte.isitweatherinfo.di.component

import android.app.Application
import com.terabyte.data.di.module.GeocoderModule
import com.terabyte.data.di.module.NetworkModule
import com.terabyte.data.di.module.RepositoryModule
import com.terabyte.isitweatherinfo.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class,
        GeocoderModule::class,
        NetworkModule::class
    ]
)
@Singleton
interface AppComponent {

    fun activityComponentFactory(): ActivityComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

}