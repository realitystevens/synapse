package com.synapseplus.impact

import com.synapseplus.core.Env
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

@kotlinx.serialization.Serializable
data class DashboardTotals(
    val trees: Double? = 0.0, 
    val meals: Double? = 0.0, 
    val water: Double? = 0.0
)

class ImpactApi(private val client: HttpClient) {
    suspend fun me(): DashboardTotals = 
        client.get("${Env.baseUrl}/api/impact/dashboard/me/").body()
    
    suspend fun group(groupId: Long): DashboardTotals = 
        client.get("${Env.baseUrl}/api/impact/dashboard/group/$groupId/").body()
    
    suspend fun global(): DashboardTotals = 
        client.get("${Env.baseUrl}/api/impact/dashboard/global/").body()
}
