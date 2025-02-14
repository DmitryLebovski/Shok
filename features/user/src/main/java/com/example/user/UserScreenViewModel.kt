package com.example.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserScreenViewModel @Inject constructor(
    private val getInfoUseCase: GetUserInfoUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UsersUiState>(UsersUiState.Loading)
    val uiState: StateFlow<UsersUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update { UsersUiState.Loading }
            getInfoUseCase()
                .onFailure { throwable ->
                    _uiState.update { UsersUiState.Error(throwable) }
                }
                .onSuccess { userData ->
                    _uiState.update { UsersUiState.Success(userData) }
                }
        }
    }
}