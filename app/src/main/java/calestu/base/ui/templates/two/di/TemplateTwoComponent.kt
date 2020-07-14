package calestu.base.ui.templates.two.di

import calestu.base.ui.templates.two.TemplateTwoActivity
import dagger.Subcomponent

@Subcomponent(modules = [TemplateTwoModule::class])
interface TemplateTwoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TemplateTwoComponent
    }

    fun inject(activity: TemplateTwoActivity)
}
