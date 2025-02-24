package com.example.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class UserScreenViewModel (
    private val getInfoUseCase: GetUserInfoUseCase,
    private val token: String
) : ViewModel() {
    private val _uiState = MutableStateFlow<UsersUiState>(UsersUiState.Loading)
    val uiState: StateFlow<UsersUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update { UsersUiState.Loading }
            getInfoUseCase(token = token)
                .onFailure { throwable ->
                    _uiState.update { UsersUiState.Error(throwable) }
                }
                .onSuccess { userData ->
                    _uiState.update { UsersUiState.Success(userData) }
                }
        }
    }
}