package com.synapseplus.notifications

actual class PushGateway actual constructor() {
    private var listener: PushListener? = null
    
    actual suspend fun initialize(appId: String) { 
        /* Desktop notification implementation */ 
    }
    
    actual suspend fun setExternalUserId(userId: String) { 
        /* Desktop user ID handling */ 
    }
    
    actual suspend fun getDeviceId(): String? = null
    
    actual fun registerListener(listener: PushListener) { 
        this.listener = listener 
    }
}
