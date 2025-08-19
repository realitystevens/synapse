package com.synapseplus.di

import com.synapseplus.auth.AuthApi
import com.synapseplus.auth.JwtStorage
import com.synapseplus.habits.HabitsApi
import com.synapseplus.accounts.MeApi
import com.synapseplus.impact.ImpactApi
import com.synapseplus.network.HttpClientFactory
import io.ktor.client.*
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformEngine(): io.ktor.client.engine.HttpClientEngine

val appModule: Module = module {
    single { JwtStorage() }
    single<HttpClient> { 
        HttpClientFactory(platformEngine()) { 
            get<JwtStorage>().current() 
        }.create() 
    }
    single { AuthApi(get()) }
    single { MeApi(get()) }
    single { HabitsApi(get()) }
    single { ImpactApi(get()) }
}
