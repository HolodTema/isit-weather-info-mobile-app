package com.terabyte.isitweatherinfo.application

import android.app.Application
import com.terabyte.isitweatherinfo.di.component.AppComponent
import com.terabyte.isitweatherinfo.di.component.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}