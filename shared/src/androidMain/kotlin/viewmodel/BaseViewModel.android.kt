package com.synapseplus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual open class BaseViewModel actual constructor(): ViewModel() {
    actual val scope = viewModelScope
    actual open fun onCleared() { 
        super.onCleared() 
    }
}
