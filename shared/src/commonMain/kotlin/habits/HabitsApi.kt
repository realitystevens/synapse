package com.synapseplus.habits

import com.synapseplus.core.Env
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class HabitsApi(private val client: HttpClient) {
    suspend fun listGroups(): List<GroupDTO> = 
        client.get("${Env.baseUrl}/api/habits/groups/").body()
    
    suspend fun listHabits(): List<HabitDTO> = 
        client.get("${Env.baseUrl}/api/habits/").body()
    
    suspend fun createCompletion(habitId: Long, metadata: Map<String, Any?> = emptyMap()): CompletionDTO =
        client.post("${Env.baseUrl}/api/habits/completions/") {
            setBody(mapOf("habit" to habitId, "metadata" to metadata))
            contentType(ContentType.Application.Json)
        }.body()
}
