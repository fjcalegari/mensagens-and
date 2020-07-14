package calestu.base.di

import android.content.Context
import calestu.base.ui.appone.di.AppOneComponent
import calestu.base.ui.categorias.di.CategoriasComponent
import calestu.base.ui.frasedetail.di.FraseDetailComponent
import calestu.base.ui.frases.di.FrasesComponent
import calestu.base.ui.home.di.HomeComponent
import calestu.base.ui.launcher.di.LauncherComponent
import calestu.base.ui.main.di.MainComponent
import calestu.base.ui.templates.two.di.TemplateTwoComponent
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

    fun appOneComponent(): AppOneComponent.Factory

    fun templateTwoComponent(): TemplateTwoComponent.Factory

    fun mainComponent(): MainComponent.Factory

    fun frasesComponent(): FrasesComponent.Factory
    fun fraseDetailComponent(): FraseDetailComponent.Factory

    fun categoriasComponent(): CategoriasComponent.Factory

    fun homeComponent(): HomeComponent.Factory

}

@Module(
    subcomponents = [
        LauncherComponent::class,
        AppOneComponent::class,
        MainComponent::class,
        HomeComponent::class,
        TemplateTwoComponent::class,
        FrasesComponent::class,
        FraseDetailComponent::class,
        CategoriasComponent::class
    ]
)
object SubcomponentsModule