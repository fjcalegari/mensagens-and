package calestu.base.ui.header

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HeaderViewModel @Inject constructor(
) : ViewModel() {

    private val _label = MutableLiveData<String>().apply { value = "Header View" }
    val label: LiveData<String> = _label

}
