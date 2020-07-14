package calestu.base.ui.templates.two.di

import androidx.lifecycle.ViewModel
import calestu.base.di.ViewModelKey
import calestu.base.ui.categorias.CategoriasViewModel
import calestu.base.ui.frases.FrasesViewModel
import calestu.base.ui.templates.two.TemplateTwoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TemplateTwoModule {

    @Binds
    @IntoMap
    @ViewModelKey(TemplateTwoViewModel::class)
    abstract fun bindViewModel(viewModel: TemplateTwoViewModel): ViewModel

}
