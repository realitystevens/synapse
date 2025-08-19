package com.synapseplus.viewmodel

import com.synapseplus.habits.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class HabitListState(val loading: Boolean = false, val habits: List<HabitDTO> = emptyList(), val error: String? = null)

data class CompletionState(val submitting: Boolean = false, val success: Boolean = false, val error: String? = null)

class HabitListVM(private val api: HabitsApi): BaseViewModel() {
    private val _state = MutableStateFlow(HabitListState())
    val state: StateFlow<HabitListState> = _state

    fun load() { 
        scope.launch {
            _state.value = HabitListState(loading = true)
            runCatching { api.listHabits() }
                .onSuccess { _state.value = HabitListState(habits = it) }
                .onFailure { _state.value = HabitListState(error = it.message) }
        }
    }
}

class CompletionVM(private val api: HabitsApi): BaseViewModel() {
    private val _state = MutableStateFlow(CompletionState())
    val state: StateFlow<CompletionState> = _state

    fun submit(habitId: Long, meta: Map<String, Any?>) { 
        scope.launch {
            _state.value = CompletionState(submitting = true)
            runCatching { api.createCompletion(habitId, meta) }
                .onSuccess { _state.value = CompletionState(success = true) }
                .onFailure { _state.value = CompletionState(error = it.message) }
        }
    }
}
