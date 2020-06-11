package calestu.base.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import calestu.base.data.Result.Success
import calestu.base.domain.usecase.SyncUseCase
import calestu.base.util.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val syncUseCase: SyncUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _syncSuccess = MutableLiveData<Event<Unit>>()
    val syncSuccess: LiveData<Event<Unit>> = _syncSuccess

//    init {
//        sync()
//    }
//
//    fun sync() {
//        _dataLoading.value = true
//
//        viewModelScope.launch {
//            delay(2000)
//            syncUseCase().let { result ->
//                if (result is Success) {
//                    _syncSuccess.value = Event(Unit)
//                }
//            }
//            _dataLoading.value = false
//        }
//    }

}
