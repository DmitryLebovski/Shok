package com.example.notifications

sealed interface NotificationsUiState {
    object Loading : NotificationsUiState
    class Success(val notificationResponse: NotificationsResponse) : NotificationsUiState
    class Error(val error: Throwable) : NotificationsUiState
}