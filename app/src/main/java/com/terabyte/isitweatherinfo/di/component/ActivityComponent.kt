package com.terabyte.isitweatherinfo.di.component

import com.terabyte.isitweatherinfo.di.scope.ActivityScope
import dagger.Subcomponent

@Subcomponent(
    modules = []
)
@ActivityScope
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }
}