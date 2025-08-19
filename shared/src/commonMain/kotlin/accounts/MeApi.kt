package com.synapseplus.accounts

import com.synapseplus.core.Env
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

@kotlinx.serialization.Serializable
data class MeDTO(
    val id: Long, 
    val username: String, 
    val email: String? = null, 
    val timezone: String? = null,
    val onesignal_player_id: String? = null, 
    val city: String? = null, 
    val country: String? = null
)

class MeApi(private val client: HttpClient) {
    suspend fun getMe(): List<MeDTO> = client.get("${Env.baseUrl}/api/accounts/me/").body()
    
    suspend fun patchMe(payload: Map<String, Any?>): MeDTO = client.request("${Env.baseUrl}/api/accounts/me/1/") {
        method = io.ktor.http.HttpMethod.Patch
        setBody(payload)
    }.body()
}
