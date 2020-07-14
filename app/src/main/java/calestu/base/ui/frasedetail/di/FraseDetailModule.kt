package calestu.base.ui.frasedetail.di

import androidx.lifecycle.ViewModel
import calestu.base.di.ViewModelKey
import calestu.base.ui.frasedetail.FraseDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FraseDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(FraseDetailViewModel::class)
    abstract fun bindViewModel(viewModel: FraseDetailViewModel): ViewModel
}
