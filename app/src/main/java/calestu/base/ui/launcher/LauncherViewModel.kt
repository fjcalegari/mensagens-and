package calestu.base.ui.launcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import calestu.base.BuildConfig
import calestu.base.data.Result.Success
import calestu.base.domain.usecase.SyncUseCase
import calestu.base.util.APP_TEMPLATE_ONE
import calestu.base.util.Event
import calestu.base.util.checkAllMatched
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LauncherViewModel @Inject constructor(
    private val syncUseCase: SyncUseCase
) : ViewModel() {

    private val _launchDestination = MutableLiveData<Event<LaunchDestination>>()
    val launchDestination: LiveData<Event<LaunchDestination>> = _launchDestination

    init {
        sync()
    }

    fun sync() {

        viewModelScope.launch {
            delay(500)
            syncUseCase().let { result ->
                if (result is Success) {
                    when (BuildConfig.TEMPLATE) {
                        APP_TEMPLATE_ONE -> _launchDestination.value = Event(LaunchDestination.APP_ONE)
                        else -> _launchDestination.value = Event(LaunchDestination.MAIN_ACTIVITY)
                    }.checkAllMatched
                }
            }
        }
    }

}

enum class LaunchDestination {
    MAIN_ACTIVITY,
    APP_ONE
}
