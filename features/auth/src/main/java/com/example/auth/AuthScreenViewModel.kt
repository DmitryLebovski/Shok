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

    fun loadToken(code: String): Boolean {
        viewModelScope.launch {
            getTokenUseCase(code = code)
                .onSuccess { token ->
                    try {
                        Log.d("CODEXGETTINGTOKEN", token.accessToken)
                        _token.emit(token)
                    } catch (e: Exception) {
                        Log.d("CODEXERROR", e.toString())
                    }
                }
                .onFailure { throwable ->
                    Log.d("CODEXERROR", "$throwable $code")
                }
        }
        return true
    }
}

