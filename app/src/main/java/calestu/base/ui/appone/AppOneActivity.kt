package calestu.base.ui.appone

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import calestu.base.BaseApplication
import calestu.base.R
import calestu.base.data.entity.Frase
import calestu.base.databinding.ActivityAppOneBinding
import calestu.base.util.EventObserver
import calestu.base.util.fadeIn
import calestu.base.util.fadeOut
import calestu.base.util.viewModelProvider
import javax.inject.Inject

class AppOneActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: AppOneViewModel

    private lateinit var binding: ActivityAppOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent.appOneComponent().create()
            .inject(this)

        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(viewModelFactory)

        binding = (DataBindingUtil.setContentView(
            this,
            R.layout.activity_app_one
        ) as ActivityAppOneBinding).apply {
            lifecycleOwner = this@AppOneActivity
            viewmodel = viewModel
        }

        viewModel.loadFrase()

        viewModel.refresh.observe(this, EventObserver {
            refreshFrase(it)
        })
    }

    private fun refreshFrase(fr: Frase) {
        if (binding.containerFrase.visibility == View.GONE) fadeIn(fr) else fadeOut(fr)
    }

    private fun fadeIn(fr: Frase) {
        binding.frase = fr
        binding.containerFrase.fadeIn()
    }

    private fun fadeOut(fr: Frase) {
        binding.containerFrase.fadeOut(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                binding.frase = null
                fadeIn(fr)
            }
        })
    }

}