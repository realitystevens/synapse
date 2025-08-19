package com.synapseplus.viewmodel

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    val scope: CoroutineScope
    open fun onCleared()
}
