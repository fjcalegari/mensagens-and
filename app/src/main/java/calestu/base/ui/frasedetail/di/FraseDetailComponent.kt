package calestu.base.ui.frasedetail.di

import calestu.base.ui.frasedetail.FraseDetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [FraseDetailModule::class])
interface FraseDetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FraseDetailComponent
    }

    fun inject(fragment: FraseDetailFragment)
}
