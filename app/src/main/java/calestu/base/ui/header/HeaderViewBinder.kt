package calestu.base.ui.header

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import calestu.base.R
import calestu.base.databinding.LayoutHeaderBinding

class HeaderViewBinder(
    private val root: ViewGroup,
    private val viewModel: HeaderViewModel
) {

    private lateinit var binding: LayoutHeaderBinding

    fun createBinding(): LayoutHeaderBinding {
        val layoutInflater = LayoutInflater.from(root.context)
        binding = LayoutHeaderBinding.inflate(layoutInflater, root, false)
//        binding = DataBindingUtil.inflate<LayoutHeaderBinding>root
//            LayoutInflater.from(root.context),
//            R.layout.fragment_home,
//            root,
//            false
//        )
        return binding
    }

    fun bind() {
        binding.viewmodel = viewModel
        binding.executePendingBindings()
    }

}