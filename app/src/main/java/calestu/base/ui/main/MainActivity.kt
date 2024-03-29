package calestu.base.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import calestu.base.BaseApplication
import calestu.base.R
import calestu.base.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private lateinit var viewDataBinding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent.mainComponent().create()
            .inject(this)

        super.onCreate(savedInstanceState)

        viewDataBinding = (DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding).apply {
            lifecycleOwner = this@MainActivity
            viewmodel = viewModel
        }

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

//        viewModel.syncSuccess.observe(this, EventObserver {
//            showHome()
//        })
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        navController = findNavController(R.id.nav_host)
    }

//    private fun showHome() {
//        navController.navigate(
//            R.id.home_fragment_dest,
//            null,
//            NavOptions.Builder()
//                .setPopUpTo(
//                    R.id.placeholder_fragment_dest,
//                    true
//                )
//                .setLaunchSingleTop(true)
//                .build()
//        )
//    }

}
