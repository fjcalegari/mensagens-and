package calestu.base.ui.launcher

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import calestu.base.BaseApplication
import calestu.base.ui.appone.AppOneActivity
import calestu.base.ui.templates.two.TemplateTwoActivity
import calestu.base.util.EventObserver
import calestu.base.util.checkAllMatched
import javax.inject.Inject

class LauncherActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LauncherViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(LauncherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent.launcherComponent().create()
            .inject(this)

        super.onCreate(savedInstanceState)

        viewModel.launchDestination.observe(this, EventObserver { destination ->
            when (destination) {
                LaunchDestination.MAIN_ACTIVITY -> startActivity(
                    Intent(
                        this,
                        TemplateTwoActivity::class.java
                    )
                )
                LaunchDestination.APP_ONE -> startActivity(Intent(this, AppOneActivity::class.java))
            }.checkAllMatched
            finish()
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUIAndNavigation(this)
        }
    }

    private fun hideSystemUIAndNavigation(activity: Activity) {
        val decorView: View = activity.window.decorView
        decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }


}