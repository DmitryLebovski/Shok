package com.example.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.user.GetCombinedUserNotificationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class NotificationScreenViewModel (
    private val getCombinedUserNotificationsUseCase: GetCombinedUserNotificationsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<NotificationsUiState>(NotificationsUiState.Loading)
    val uiState: StateFlow<NotificationsUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update { NotificationsUiState.Loading }
            getCombinedUserNotificationsUseCase()
                .onFailure { throwable ->
                    _uiState.update { NotificationsUiState.Error(throwable) }
                }
                .onSuccess { notifications ->
                    _uiState.update { NotificationsUiState.Success(notifications.notifications) }
                }
        }
    }
}