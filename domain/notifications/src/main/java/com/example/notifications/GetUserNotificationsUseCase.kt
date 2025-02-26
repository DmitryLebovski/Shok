package com.example.notifications

import javax.inject.Inject

class GetUserNotificationsUseCase @Inject constructor(
    private val notificationsRepository: NotificationsRepository
) {
    suspend operator fun invoke(): Result<NotificationsResponse> {
        return notificationsRepository.getUsersNotifications()
    }
}