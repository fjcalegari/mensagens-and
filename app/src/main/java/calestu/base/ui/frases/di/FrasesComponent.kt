package calestu.base.ui.frases.di

import calestu.base.ui.frases.FrasesFragment
import dagger.Subcomponent

@Subcomponent(modules = [FrasesModule::class])
interface FrasesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FrasesComponent
    }

    fun inject(fragment: FrasesFragment)
}
