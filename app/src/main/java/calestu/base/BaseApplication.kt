package calestu.base

import android.app.Application
import calestu.base.di.AppComponent
import calestu.base.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import timber.log.Timber
import timber.log.Timber.DebugTree

open class BaseApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }

}
