package com.example.notifications

interface ProviderNotificationViewModel {
    fun notificationUseCase(): GetUserNotificationsUseCase
}