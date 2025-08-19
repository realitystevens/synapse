package com.synapseplus.notifications

import android.app.Application
import com.onesignal.OneSignal

actual class PushGateway actual constructor() {
    private var listener: PushListener? = null
    private var initialized = false

    suspend fun initWithApp(app: Application, appId: String) {
        if (initialized) return
        OneSignal.initWithContext(app)
        OneSignal.setAppId(appId)
        OneSignal.setNotificationOpenedHandler { result ->
            val map = result.notification.additionalData?.toMap() ?: emptyMap()
            listener?.onNotification(map)
        }
        initialized = true
    }

    actual suspend fun initialize(appId: String) { 
        /* call initWithApp from platform layer */ 
    }
    
    actual suspend fun setExternalUserId(userId: String) { 
        OneSignal.setExternalUserId(userId) 
    }
    
    actual suspend fun getDeviceId(): String? = 
        OneSignal.getDeviceState()?.userId
    
    actual fun registerListener(listener: PushListener) { 
        this.listener = listener 
    }
}
