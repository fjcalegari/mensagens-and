package calestu.base.ui.frases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import calestu.base.data.Result
import calestu.base.data.Result.Success
import calestu.base.data.entity.Categoria
import calestu.base.data.entity.Frase
import calestu.base.data.repository.FraseRepository
import calestu.base.util.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class FrasesViewModel @Inject constructor(
    private val fraseRepository: FraseRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<Frase>>().apply { value = emptyList() }
    val items: LiveData<List<Frase>> = _items

    private val _openFraseEvent = MutableLiveData<Event<String>>()
    val openFraseEvent: LiveData<Event<String>> = _openFraseEvent

    init {
        loadFrases()
    }

    fun loadFrases() {
        viewModelScope.launch {
            val frasesResult = fraseRepository.getFrases()

            if (frasesResult is Result.Success) {
                val frases = frasesResult.data
                _items.value = ArrayList(frases)
            } else {
                _items.value = emptyList()
            }

        }
    }

    fun openFrase(fraseId: String) {
        _openFraseEvent.value = Event(fraseId)
    }

}