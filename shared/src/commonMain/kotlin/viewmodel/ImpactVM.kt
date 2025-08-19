package com.synapseplus.viewmodel

import com.synapseplus.impact.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ImpactState(val loading: Boolean = false, val me: DashboardTotals? = null, val error: String? = null)

class ImpactVM(private val api: ImpactApi): BaseViewModel() {
    private val _state = MutableStateFlow(ImpactState())
    val state: StateFlow<ImpactState> = _state

    fun loadMe() { 
        scope.launch {
            _state.value = ImpactState(loading = true)
            runCatching { api.me() }
                .onSuccess { _state.value = ImpactState(me = it) }
                .onFailure { _state.value = ImpactState(error = it.message) }
        }
    }
}
