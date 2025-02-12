package com.example.domain.user

import com.example.domain.notifications.NotificationsResponse

interface UserRepository {
    suspend fun getUserInfo(): Result<User>
    suspend fun getUsersNotifications(): Result<NotificationsResponse>
}