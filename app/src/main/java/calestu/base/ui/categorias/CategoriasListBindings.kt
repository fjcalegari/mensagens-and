package calestu.base.ui.categorias

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import calestu.base.data.entity.Categoria

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Categoria>) {
    (listView.adapter as CategoriasAdapter).submitList(items)
}