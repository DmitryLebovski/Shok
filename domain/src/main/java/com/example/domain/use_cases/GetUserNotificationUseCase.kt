package com.example.domain.use_cases

import com.example.domain.notifications.NotificationsResponse
import com.example.domain.user.UserRepository
import javax.inject.Inject

class GetUserNotificationUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<NotificationsResponse> {
        return userRepository.getUsersNotifications()
    }
}