package calestu.base.ui.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import calestu.base.BaseApplication
import calestu.base.ui.main.MainActivity
import calestu.base.util.EventObserver
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

        viewModel.syncSuccess.observe(this, EventObserver {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }


}