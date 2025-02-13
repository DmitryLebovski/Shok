package com.example.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserScreenViewModel @Inject constructor(
    private val getInfoUseCase: GetUserInfoUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _userInfo = MutableStateFlow<UserInfo?>(null)
    val userInfo: StateFlow<UserInfo?> = _userInfo

    init {
        viewModelScope.launch {
//            Log.d("CODEXTOKENVM", tokenRepository.getToken().toString())
            _uiState.update { UiState.Loading }
            getInfoUseCase()
                .onFailure { throwable ->
//                    Log.d("CODEXTOKENVMFAIL", tokenRepository.getToken().toString())
                    _uiState.update { UiState.Error(throwable) }
                }
                .onSuccess {
//                    Log.d("CODEXTOKENVMSUCC", tokenRepository.getToken().toString())
                    _userInfo.emit(it)
                    _uiState.update { UiState.Success }
                }
        }
    }
}