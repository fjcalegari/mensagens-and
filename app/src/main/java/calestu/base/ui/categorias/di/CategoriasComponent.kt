package calestu.base.ui.categorias.di

import calestu.base.ui.categorias.CategoriasFragment
import dagger.Subcomponent

@Subcomponent(modules = [CategoriasModule::class])
interface CategoriasComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CategoriasComponent
    }

    fun inject(fragment: CategoriasFragment)
}
