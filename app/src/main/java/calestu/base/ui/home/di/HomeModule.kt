package calestu.base.ui.home.di

import androidx.lifecycle.ViewModel
import calestu.base.di.ViewModelKey
import calestu.base.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModel(viewModel: HomeViewModel): ViewModel
}
