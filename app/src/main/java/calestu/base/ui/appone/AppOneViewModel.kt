package calestu.base.ui.appone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import calestu.base.data.Result
import calestu.base.data.entity.Frase
import calestu.base.data.repository.FraseRepository
import calestu.base.util.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppOneViewModel @Inject constructor(
    private val fraseRepository: FraseRepository
) : ViewModel() {

    private val _refresh = MutableLiveData<Event<Frase>>()
    val refresh: LiveData<Event<Frase>> = _refresh

    fun loadFrase() {
        viewModelScope.launch {
            fraseRepository.getRandom().let { result ->
                if (result is Result.Success) {
                    _refresh.value = Event(result.data)
                }
            }
        }
    }

}
