package calestu.base.ui.frases

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import calestu.base.data.entity.Frase

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Frase>) {
    (listView.adapter as FrasesAdapter).submitList(items)
}