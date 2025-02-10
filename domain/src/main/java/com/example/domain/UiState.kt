package com.example.domain

sealed interface UiState {
    object Loading : UiState
    object Success : UiState
    class Error(val error: Throwable) : UiState
}