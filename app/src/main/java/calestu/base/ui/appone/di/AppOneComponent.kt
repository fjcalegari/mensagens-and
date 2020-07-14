package calestu.base.ui.appone.di

import calestu.base.ui.appone.AppOneActivity
import dagger.Subcomponent

@Subcomponent(modules = [AppOneModule::class])
interface AppOneComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AppOneComponent
    }

    fun inject(activity: AppOneActivity)
}
