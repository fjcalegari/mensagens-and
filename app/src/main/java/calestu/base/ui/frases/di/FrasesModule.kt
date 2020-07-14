package calestu.base.ui.frases.di

import androidx.lifecycle.ViewModel
import calestu.base.di.ViewModelKey
import calestu.base.ui.frases.FrasesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FrasesModule {

    @Binds
    @IntoMap
    @ViewModelKey(FrasesViewModel::class)
    abstract fun bindViewModel(viewModel: FrasesViewModel): ViewModel
}
