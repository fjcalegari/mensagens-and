package calestu.base.ui.launcher.di

import calestu.base.ui.launcher.LauncherActivity
import dagger.Subcomponent

@Subcomponent(modules = [LauncherModule::class])
interface LauncherComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LauncherComponent
    }

    fun inject(activity: LauncherActivity)
}
