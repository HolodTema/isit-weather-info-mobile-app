package com.terabyte.isitweatherinfo.di.module

import android.app.Application
import android.content.Context
import com.terabyte.domain.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    companion object {

        @Provides
        @ApplicationContext
        @Singleton
        fun provideApplicationContext(application: Application): Context {
            return application.applicationContext
        }
    }
}