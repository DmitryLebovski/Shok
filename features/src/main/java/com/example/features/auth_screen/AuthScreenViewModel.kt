package com.example.features.auth_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.GetTokenUseCase
import com.example.domain.token.TokenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthScreenViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val tokenRepository: TokenRepository
) : ViewModel() {
    private val _authCode = MutableStateFlow<String?>(null)
    val authCode: StateFlow<String?> = _authCode

    fun setAuthCode(code: String) {
        _authCode.value = code
        loadToken()
    }

    private fun loadToken() {
        viewModelScope.launch {

            authCode.value?.let { code ->
                getTokenUseCase(code = code)
                    .onSuccess { token ->
                        try {
                            Log.d("CODEXCTOKEN", token?.accessToken.toString())
                            token?.let { tokenRepository.saveToken(it) }
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
}

