package com.synapseplus.di

import io.ktor.client.engine.java.*

actual fun platformEngine() = Java.create()
