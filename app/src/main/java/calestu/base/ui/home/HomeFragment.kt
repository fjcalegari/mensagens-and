package calestu.base.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import calestu.base.BaseApplication
import calestu.base.BuildConfig
import calestu.base.databinding.FragmentHomeBinding
import calestu.base.ui.header.HeaderViewBinder
import calestu.base.ui.header.HeaderViewModel
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: FragmentHomeBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BaseApplication).appComponent.homeComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadFrase()

        Timber.i("BuildConfig.FLAVOR: ${BuildConfig.FLAVOR}")
        Timber.i("BuildConfig.APPLICATION_ID: ${BuildConfig.APPLICATION_ID}")
    }

}
