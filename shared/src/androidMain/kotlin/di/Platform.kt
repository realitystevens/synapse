package com.synapseplus.di

import io.ktor.client.engine.okhttp.*

actual fun platformEngine() = OkHttp.create()
