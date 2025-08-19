package com.synapseplus.notifications

interface PushListener { 
    fun onNotification(data: Map<String, Any?>) 
}

expect class PushGateway() {
    suspend fun initialize(appId: String)
    suspend fun setExternalUserId(userId: String)
    suspend fun getDeviceId(): String?
    fun registerListener(listener: PushListener)
}
