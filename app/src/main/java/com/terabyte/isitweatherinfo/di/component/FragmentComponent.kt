package com.terabyte.isitweatherinfo.di.component

import com.terabyte.isitweatherinfo.activity.MainActivity
import com.terabyte.isitweatherinfo.di.scope.ActivityScope
import com.terabyte.isitweatherinfo.di.scope.FragmentScope
import com.terabyte.isitweatherinfo.fragment.ChooseCityFragment
import com.terabyte.isitweatherinfo.fragment.ErrorFragment
import com.terabyte.isitweatherinfo.fragment.WeatherDetailsFragment
import dagger.Subcomponent

@Subcomponent(
    modules = []
)
@FragmentScope
interface FragmentComponent {

    fun inject(fragment: ChooseCityFragment)

    fun inject(fragment: ErrorFragment)

    fun inject(fragment: WeatherDetailsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }
}