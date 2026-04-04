package com.terabyte.data.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.terabyte.data.BuildConfig
import com.terabyte.data.di.qualifier.TemperatureApiRetrofit
import com.terabyte.data.di.qualifier.TimeApiRetrofit
import com.terabyte.data.storage.remote.temperature_api.TemperatureApiStorage
import com.terabyte.data.storage.remote.temperature_api.TemperatureApiStorageImpl
import com.terabyte.data.storage.remote.temperature_api.service.TemperatureService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
interface NetworkModule {

    @Singleton
    @Binds
    fun bindTemperatureApiStorage(storage: TemperatureApiStorageImpl): TemperatureApiStorage

    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                //BODY = log url, http method, response code, handle-time,
                //headers + request/response body
                //
                //logs will be in AndroidStudio Logcat.
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        @JvmStatic
        @Provides
        @TimeApiRetrofit
        @Singleton
        fun provideTimeApiRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_TIME_API_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        @JvmStatic
        @Provides
        @TemperatureApiRetrofit
        @Singleton
        fun provideTemperatureApiRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_TEMPERATURE_API_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideTemperatureService(@TemperatureApiRetrofit retrofit: Retrofit): TemperatureService {
            return retrofit.create<TemperatureService>()
        }
    }
}