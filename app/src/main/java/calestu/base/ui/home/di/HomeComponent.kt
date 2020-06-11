package calestu.base.ui.home.di

import calestu.base.ui.header.di.HeaderModule
import calestu.base.ui.home.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class, HeaderModule::class])
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(fragment: HomeFragment)
}
