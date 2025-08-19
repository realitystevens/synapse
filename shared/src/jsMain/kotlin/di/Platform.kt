package com.synapseplus.di

import io.ktor.client.engine.js.*

actual fun platformEngine() = Js.create()
