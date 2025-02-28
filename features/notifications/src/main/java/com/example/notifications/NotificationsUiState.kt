package com.example.notifications

sealed interface NotificationsUiState {
    data object Loading : NotificationsUiState
    class Success(val notificationResponse: NotificationsResponse) : NotificationsUiState
    class Error(val error: Throwable) : NotificationsUiState
}