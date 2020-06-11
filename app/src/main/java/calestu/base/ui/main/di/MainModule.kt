package calestu.base.ui.main.di

import androidx.lifecycle.ViewModel
import calestu.base.di.ViewModelKey
import calestu.base.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel
}
