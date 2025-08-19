package com.synapseplus.notifications

actual class PushGateway actual constructor() {
    private var listener: PushListener? = null
    
    actual suspend fun initialize(appId: String) { 
        /* Integrate OneSignal iOS SDK in Swift and bridge callbacks */ 
    }
    
    actual suspend fun setExternalUserId(userId: String) { 
        /* OneSignal.setExternalUserId */ 
    }
    
    actual suspend fun getDeviceId(): String? = null
    
    actual fun registerListener(listener: PushListener) { 
        this.listener = listener 
    }
}
