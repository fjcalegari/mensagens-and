package calestu.base.ui.frases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import calestu.base.data.entity.Frase
import calestu.base.databinding.ItemFraseBinding
import calestu.base.ui.frases.FrasesAdapter.ViewHolder

class FrasesAdapter(private val viewModel: FrasesViewModel) :
    ListAdapter<Frase, ViewHolder>(FrasesDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemFraseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: FrasesViewModel, item: Frase) {

            binding.viewmodel = viewModel
            binding.frase = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFraseBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class FrasesDiffCallback : DiffUtil.ItemCallback<Frase>() {
    override fun areItemsTheSame(oldItem: Frase, newItem: Frase): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Frase, newItem: Frase): Boolean {
        return oldItem == newItem
    }
}
