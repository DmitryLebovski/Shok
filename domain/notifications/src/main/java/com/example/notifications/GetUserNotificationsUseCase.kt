package com.example.notifications

import javax.inject.Inject

class GetUserNotificationsUseCase @Inject constructor(
    private val notificationsRepository: NotificationsRepository,
) {
    suspend operator fun invoke(token: String): Result<NotificationsResponse> {
        return notificationsRepository.getUsersNotifications(token = token)
    }
}