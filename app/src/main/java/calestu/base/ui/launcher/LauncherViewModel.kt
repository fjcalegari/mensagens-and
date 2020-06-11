package calestu.base.ui.launcher

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

class LauncherViewModel @Inject constructor(
    private val syncUseCase: SyncUseCase
) : ViewModel() {

    private val _syncSuccess = MutableLiveData<Event<Unit>>()
    val syncSuccess: LiveData<Event<Unit>> = _syncSuccess

    init {
        sync()
    }

    fun sync() {

        viewModelScope.launch {
            delay(500)
            syncUseCase().let { result ->
                if (result is Success) {
                    _syncSuccess.value = Event(Unit)
                }
            }
        }
    }

}
