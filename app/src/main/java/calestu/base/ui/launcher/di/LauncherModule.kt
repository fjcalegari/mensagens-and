package calestu.base.ui.launcher.di

import androidx.lifecycle.ViewModel
import calestu.base.di.ViewModelKey
import calestu.base.ui.launcher.LauncherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LauncherModule {

    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    abstract fun bindViewModel(viewModel: LauncherViewModel): ViewModel
}
