package com.synapseplus.di

import io.ktor.client.engine.darwin.*

actual fun platformEngine() = Darwin.create()
