package calestu.base.ui.categorias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import calestu.base.data.Result
import calestu.base.data.Result.Success
import calestu.base.data.entity.Categoria
import calestu.base.data.repository.CategoriaRepository
import calestu.base.util.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriasViewModel @Inject constructor(
    private val categoriaRepository: CategoriaRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<Categoria>>().apply { value = emptyList() }
    val items: LiveData<List<Categoria>> = _items

    private val _openFrasesCategoriaEvent = MutableLiveData<Event<Int>>()
    val openFrasesCategoriaEvent: LiveData<Event<Int>> = _openFrasesCategoriaEvent

    init {
        loadCategorias()
    }

    fun loadCategorias() {
        viewModelScope.launch {
            val categoriasResult = categoriaRepository.getCategorias()

            if (categoriasResult is Success) {
                val categorias = categoriasResult.data
                _items.value = ArrayList(categorias)
            } else {
                _items.value = emptyList()
            }

        }
    }

    fun openFrasesCategoria(categoriaId: Int) {
        _openFrasesCategoriaEvent.value = Event(categoriaId)
    }

}