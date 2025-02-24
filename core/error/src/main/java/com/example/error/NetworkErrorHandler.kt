package com.example.error

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow

object GlobalErrorHandler {
    private val _errorFlow = MutableStateFlow<Throwable?>(null)
    val errorFlow: SharedFlow<Throwable?> = _errorFlow

    suspend fun emitError(error: Throwable) {
        _errorFlow.emit(error)
    }
}