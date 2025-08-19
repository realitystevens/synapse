package com.synapseplus.viewmodel

import com.synapseplus.auth.AuthApi
import com.synapseplus.auth.JwtStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthState(val loading: Boolean = false, val token: String? = null, val error: String? = null)

class AuthVM(private val api: AuthApi, private val jwt: JwtStorage): BaseViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun login(username: String, password: String) {
        scope.launch {
            _state.value = AuthState(loading = true)
            runCatching { api.login(username, password) }
                .onSuccess {
                    scope.launch { jwt.setToken(it.access) }
                    _state.value = AuthState(token = it.access)
                }
                .onFailure { _state.value = AuthState(error = it.message) }
        }
    }
}
