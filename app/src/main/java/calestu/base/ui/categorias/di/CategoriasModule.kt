package calestu.base.ui.categorias.di

import androidx.lifecycle.ViewModel
import calestu.base.di.ViewModelKey
import calestu.base.ui.categorias.CategoriasViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CategoriasModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoriasViewModel::class)
    abstract fun bindViewModel(viewModel: CategoriasViewModel): ViewModel
}
