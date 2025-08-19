package com.synapseplus.core

import io.github.aakira.napier.Napier

object Logger { 
    fun d(msg: String) = Napier.d(msg)
    fun e(t: Throwable, msg: String="") = Napier.e(msg, t) 
}
