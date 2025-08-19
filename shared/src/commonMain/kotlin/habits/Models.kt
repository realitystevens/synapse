package com.synapseplus.habits

import kotlinx.serialization.Serializable

@Serializable 
data class GroupDTO(val id: Long, val name: String, val member_count: Int = 1)

@Serializable 
data class HabitDTO(
    val id: Long, 
    val group: Long, 
    val owner: Long? = null, 
    val name: String, 
    val type: String, 
    val frequency_per_week: Int
)

@Serializable 
data class CompletionDTO(
    val id: Long, 
    val habit: Long, 
    val user: Long, 
    val completed_at: String
)
