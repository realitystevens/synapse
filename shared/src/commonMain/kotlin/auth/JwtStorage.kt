package com.synapseplus.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JwtStorage {
    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token
    
    suspend fun setToken(value: String?) { 
        _token.emit(value) 
    }
    
    fun current() = _token.value
}
