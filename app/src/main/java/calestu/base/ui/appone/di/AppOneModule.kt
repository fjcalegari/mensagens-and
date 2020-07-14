package calestu.base.ui.appone.di

import androidx.lifecycle.ViewModel
import calestu.base.di.ViewModelKey
import calestu.base.ui.appone.AppOneViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppOneModule {

    @Binds
    @IntoMap
    @ViewModelKey(AppOneViewModel::class)
    abstract fun bindViewModel(viewModel: AppOneViewModel): ViewModel
}
