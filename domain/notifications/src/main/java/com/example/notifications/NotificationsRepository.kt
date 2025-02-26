package com.example.notifications

interface NotificationsRepository {
    suspend fun getUsersNotifications(): Result<NotificationsResponse>
}