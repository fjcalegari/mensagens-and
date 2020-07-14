package calestu.base.ui.templates.two

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import calestu.base.BaseApplication
import calestu.base.R
import calestu.base.databinding.ActivityTemplateTwoBinding
import calestu.base.util.setupWithNavController

class TemplateTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTemplateTwoBinding

//    private lateinit var navController: NavController

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent.templateTwoComponent().create()
            .inject(this)

        super.onCreate(savedInstanceState)

        binding = (DataBindingUtil.setContentView(this, R.layout.activity_template_two) as ActivityTemplateTwoBinding).apply {
            lifecycleOwner = this@TemplateTwoActivity
        }

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState

//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//
//        val navController = findNavController(R.id.nav_host_fragment)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = binding.bottomNav

        val navGraphIds = listOf(R.navigation.frases_nav,R.navigation.categorias_nav,R.navigation.home_nav)

        // Setup the bottom navigation view with a list of navigation graphs
        currentNavController = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        currentNavController?.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
            navController.run {
                addOnDestinationChangedListener{ _, destination, arguments ->
                    if (isTopLevelDestination(destination.id, arguments)) {
                        showBottomNavigation()
                    } else {
                        hideBottomNavigation()
                    }
                }
            }
        })

    }

    private fun isTopLevelDestination(destinationId: Int, arguments: Bundle?): Boolean {
        val isTopLevelDestination = TOP_LEVEL_DESTINATIONS.contains(destinationId)
        if (isTopLevelDestination) {
            when(destinationId) {
                R.id.frasesScreen -> if (arguments?.containsKey("categoriaId") == true) return false
            }
            return true
        } else {
            return false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun showBottomNavigation() {
        binding.bottomNav.visibility = View.VISIBLE
    }

    private fun hideBottomNavigation() {
        binding.bottomNav.visibility = View.GONE
    }

    companion object {
        private val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.frasesScreen,
            R.id.categoriasScreen,
            R.id.homeScreen
        )
    }

}
