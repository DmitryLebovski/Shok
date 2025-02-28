package com.example.auth

import java.util.concurrent.atomic.AtomicReference

object AuthCodeHolder {
    private val _code = AtomicReference<String?>(null)
    var code: String?
        get() = _code.get()
        set(value) = _code.set(value)
    fun clearCode() = _code.set(null)
}