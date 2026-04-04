package com.terabyte.isitweatherinfo.di.component

import com.terabyte.isitweatherinfo.activity.MainActivity
import com.terabyte.isitweatherinfo.di.scope.ActivityScope
import dagger.Subcomponent

@Subcomponent(
    modules = []
)
@ActivityScope
interface ActivityComponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }
}