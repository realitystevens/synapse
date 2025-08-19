package com.synapseplus.billing

import com.revenuecat.purchases.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

actual class BillingGateway actual constructor() {
    actual fun observeEntitlements(): Flow<Entitlement> = callbackFlow {
        val listener = CustomerInfoUpdateListener { info ->
            val active = info.entitlements.active.isNotEmpty()
            trySend(Entitlement(premium = active, periodEnd = null))
        }
        Purchases.sharedInstance.addCustomerInfoUpdateListener(listener)
        awaitClose { 
            Purchases.sharedInstance.removeCustomerInfoUpdateListener(listener) 
        }
    }
    
    actual suspend fun purchase(productId: String): PurchaseResult {
        return try { 
            /* trigger purchase from platform UI; bridge result */ 
            PurchaseResult(true) 
        } catch (t: Throwable) { 
            PurchaseResult(false, t.message) 
        }
    }
    
    actual suspend fun restore(): Boolean { 
        Purchases.sharedInstance.restorePurchases()
        return true 
    }
}
