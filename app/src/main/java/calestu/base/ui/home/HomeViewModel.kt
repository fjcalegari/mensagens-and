package calestu.base.ui.home

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

class HomeViewModel @Inject constructor(
    private val fraseRepository: FraseRepository
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _frase = MutableLiveData<Frase>()
    val frase: LiveData<Frase> = _frase

    init {
        loadFrase()
    }

    fun loadFrase() {
        _dataLoading.value = true
        viewModelScope.launch {
            delay(1000)
            fraseRepository.getRandom().let { result ->
                if (result is Result.Success) {
                    _frase.value = result.data
                }
            }
            _dataLoading.value = false
        }
    }

}