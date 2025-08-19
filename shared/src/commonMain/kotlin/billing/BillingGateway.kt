package com.synapseplus.billing

import kotlinx.coroutines.flow.Flow

data class Entitlement(val premium: Boolean, val periodEnd: Long? = null)

data class PurchaseResult(val success: Boolean, val error: String? = null)

expect class BillingGateway() {
    fun observeEntitlements(): Flow<Entitlement>
    suspend fun purchase(productId: String): PurchaseResult
    suspend fun restore(): Boolean
}
