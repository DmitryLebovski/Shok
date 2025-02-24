package com.example.notifications

interface NotificationsRepository {
    suspend fun getUsersNotifications(token: String): Result<NotificationsResponse>
}