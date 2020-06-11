package calestu.base.di

import android.content.Context
import calestu.base.data.repository.CategoriaRepository
import calestu.base.ui.home.di.HomeComponent
import calestu.base.ui.launcher.di.LauncherComponent
import calestu.base.ui.main.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        LocalModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelBuilderModule::class,
        SubcomponentsModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun launcherComponent(): LauncherComponent.Factory

    fun mainComponent(): MainComponent.Factory

    fun homeComponent(): HomeComponent.Factory

}

@Module(
    subcomponents = [
        LauncherComponent::class,
        MainComponent::class,
        HomeComponent::class
    ]
)
object SubcomponentsModule