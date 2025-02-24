package com.example.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthScreenViewModel (
    private val getTokenUseCase: GetTokenUseCase,
) : ViewModel() {

    private val _token = MutableStateFlow<Token?>(null)
    val token: StateFlow<Token?> = _token

    fun loadToken(code: String) {
        viewModelScope.launch {
            getTokenUseCase(code = code)
                .onSuccess { token ->
                    try {
                        Log.d("CODEXGETTINGTOKEN", token.accessToken)
                        _token.emit(token)
                    } catch (e: Exception) {
                        Log.d("AppError", e.toString())
                    }
                }
                .onFailure { throwable ->
                    Log.d("AppError", throwable.toString())
                }
        }
    }
}

