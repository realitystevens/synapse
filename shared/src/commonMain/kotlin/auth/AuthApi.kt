package com.synapseplus.auth

import com.synapseplus.core.Env
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable 
data class TokenPair(val access: String, val refresh: String)

@Serializable 
data class LoginRequest(val username: String, val password: String)

class AuthApi(private val client: HttpClient) {
    suspend fun login(username: String, password: String): TokenPair =
        client.post("${Env.baseUrl}/api/auth/token/") {
            setBody(LoginRequest(username, password))
            contentType(ContentType.Application.Json)
        }.body()
}
