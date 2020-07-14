package calestu.base.ui.categorias

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import calestu.base.BaseApplication
import calestu.base.R
import calestu.base.databinding.FragmentCategoriasBinding
import calestu.base.databinding.FragmentFrasesBinding
import calestu.base.ui.frases.FrasesFragmentDirections
import calestu.base.util.EventObserver
import timber.log.Timber
import javax.inject.Inject

class CategoriasFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<CategoriasViewModel> { viewModelFactory }

    private lateinit var binding: FragmentCategoriasBinding

    private lateinit var listAdapter: CategoriasAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BaseApplication).appComponent.categoriasComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriasBinding.inflate(inflater, container, false).apply {
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
        viewModel.openFrasesCategoriaEvent.observe(this, EventObserver {
            openFrasesCategoriaScreen(it)
        })
    }

    private fun openFrasesCategoriaScreen(categoriaId: Int) {
        var bundle = bundleOf("categoriaId" to categoriaId, "title" to "Categoria")
        findNavController().navigate(R.id.action_categoriasScreen_to_frasesScreen, bundle)
    }

    private fun setupListAdapter() {
        val viewModel = binding.viewmodel
        if (viewModel != null) {
            listAdapter = CategoriasAdapter(viewModel)
            binding.categoriasList.adapter = listAdapter
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }

}