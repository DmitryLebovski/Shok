package com.example.features.notification_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.UiState
import com.example.domain.notifications.NotificationsResponse
import com.example.domain.use_cases.GetUserNotificationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationScreenViewModel @Inject constructor(
    private val getUserNotificationUseCase: GetUserNotificationUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _userNotifications = MutableStateFlow<NotificationsResponse?>(null)
    val userNotifications: StateFlow<NotificationsResponse?> = _userNotifications

    init {
        viewModelScope.launch {
            _uiState.update { UiState.Loading }
            getUserNotificationUseCase()
                .onFailure { throwable ->
                    _uiState.update { UiState.Error(throwable) }
                }
                .onSuccess {
                    _userNotifications.emit(it)
                    _uiState.update { UiState.Success }
                }
        }
    }
}