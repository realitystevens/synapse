package com.synapseplus.android

import android.app.Application
import com.onesignal.OneSignal
import com.revenuecat.purchases.Purchases
import com.synapseplus.core.Env
import com.synapseplus.notifications.PushGateway
import org.koin.core.context.startKoin
import com.synapseplus.di.appModule

class SynapseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // Configure environment
        Env.baseUrl = "http://10.0.2.2:8000"
        Env.oneSignalAppId = "YOUR_ONESIGNAL_APP_ID"
        Env.revenueCatKey = "YOUR_RC_PUBLIC_API_KEY"

        startKoin { modules(appModule) }

        // Init SDKs
        OneSignal.initWithContext(this)
        OneSignal.setAppId(Env.oneSignalAppId)
        Purchases.configure(this, Env.revenueCatKey)

        // Register push listener (route deep links in shared later)
        PushGateway().initWithApp(this, Env.oneSignalAppId)
    }
}
