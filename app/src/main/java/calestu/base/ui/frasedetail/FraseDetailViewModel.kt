package calestu.base.ui.frasedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import calestu.base.data.Result.Success
import calestu.base.data.entity.Frase
import calestu.base.data.repository.FraseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FraseDetailViewModel @Inject constructor(
    private val fraseRepository: FraseRepository
) : ViewModel() {

    private val _frase = MutableLiveData<Frase>()
    val frase: LiveData<Frase> = _frase

    fun loadFrase(fraseId: String) {
        viewModelScope.launch {
            val fraseResult = fraseRepository.getFraseById(fraseId)

            if (fraseResult is Success) {
                _frase.value = fraseResult.data
            }

        }
    }

}