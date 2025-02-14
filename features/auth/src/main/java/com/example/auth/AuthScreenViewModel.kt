package com.example.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthScreenViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    fun loadToken(code: String) {
        viewModelScope.launch {
            if (authRepository.getToken()?.accessToken.isNullOrEmpty()) {
                getTokenUseCase(code = code)
                    .onSuccess { token ->
                        try {
                            Log.d("CODEXCTOKEN", token?.accessToken.toString())
                            token?.let {
                                authRepository.saveToken(it)
                            }
                        } catch (e: Exception) {
                            Log.d("AppError", e.toString())
                        }
                    }
                    .onFailure { throwable ->
                        Log.d("AppError", throwable.toString())
                    }
            }
            _token.update { authRepository.getToken()?.accessToken }
        }
    }
}

