package com.synapseplus.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

actual open class BaseViewModel actual constructor() {
    actual val scope = CoroutineScope(SupervisorJob())
    actual open fun onCleared() {}
}
