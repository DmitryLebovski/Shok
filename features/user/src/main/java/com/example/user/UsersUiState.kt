package com.example.user

sealed interface UsersUiState {
    object Loading : UsersUiState
    class Success(val userData: UserInfo) : UsersUiState
    class Error(val error: Throwable) : UsersUiState
}