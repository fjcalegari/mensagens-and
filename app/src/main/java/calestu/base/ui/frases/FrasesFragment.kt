package calestu.base.ui.frases

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import calestu.base.BaseApplication
import calestu.base.databinding.FragmentFrasesBinding
import calestu.base.ui.categorias.CategoriasAdapter
import calestu.base.util.EventObserver
import timber.log.Timber
import javax.inject.Inject

class FrasesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FrasesViewModel> { viewModelFactory }

    private lateinit var binding: FragmentFrasesBinding

    private lateinit var listAdapter: FrasesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BaseApplication).appComponent.frasesComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFrasesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListAdapter()
        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.openFraseEvent.observe(this, EventObserver {
            openFraseDetails(it)
        })
    }

    private fun openFraseDetails(fraseId: String) {
        val action = FrasesFragmentDirections.actionFrasesScreenToFraseDetailScreen(fraseId)
        findNavController().navigate(action)
    }

    private fun setupListAdapter() {
        val viewModel = binding.viewmodel
        if (viewModel != null) {
            listAdapter = FrasesAdapter(viewModel)
            binding.frasesList.adapter = listAdapter
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }

}