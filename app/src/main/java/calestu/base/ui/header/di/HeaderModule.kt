package calestu.base.ui.header.di

import androidx.lifecycle.ViewModel
import calestu.base.di.ViewModelKey
import calestu.base.ui.header.HeaderViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HeaderModule {

    @Binds
    @IntoMap
    @ViewModelKey(HeaderViewModel::class)
    abstract fun bindViewModel(viewModel: HeaderViewModel): ViewModel
}
