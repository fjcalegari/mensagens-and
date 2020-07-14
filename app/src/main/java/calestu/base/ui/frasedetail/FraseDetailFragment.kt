package calestu.base.ui.frasedetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import calestu.base.BaseApplication
import calestu.base.databinding.FragmentFraseDetailBinding
import calestu.base.databinding.FragmentFrasesBinding
import calestu.base.ui.categorias.CategoriasAdapter
import calestu.base.ui.templates.two.TemplateTwoActivity
import timber.log.Timber
import javax.inject.Inject

class FraseDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FraseDetailViewModel> { viewModelFactory }

    private lateinit var binding: FragmentFraseDetailBinding

    private val args: FraseDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BaseApplication).appComponent.fraseDetailComponent().create()
            .inject(this)

//        (activity as TemplateTwoActivity).hideBottomNavigation()
    }

    override fun onDetach() {
//        (activity as TemplateTwoActivity).showBottomNavigation()
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFraseDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadFrase(args.fraseId)
    }

}