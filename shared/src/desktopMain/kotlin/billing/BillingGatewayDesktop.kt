package com.synapseplus.billing

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class BillingGateway actual constructor() {
    actual fun observeEntitlements(): Flow<Entitlement> = 
        flowOf(Entitlement(premium = false))
    
    actual suspend fun purchase(productId: String): PurchaseResult =
        PurchaseResult(false, "Desktop billing not implemented")
    
    actual suspend fun restore(): Boolean = false
}
