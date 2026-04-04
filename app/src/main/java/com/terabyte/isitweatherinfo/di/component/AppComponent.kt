package com.terabyte.isitweatherinfo.di.component

import android.app.Application
import com.terabyte.isitweatherinfo.di.module.AppModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

}