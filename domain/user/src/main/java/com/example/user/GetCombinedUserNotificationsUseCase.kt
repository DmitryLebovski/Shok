package com.example.user

import com.example.notifications.GetUserNotificationsUseCase

class GetCombinedUserNotificationsUseCase(
    private val userInfoUseCase: GetUserInfoUseCase,
    private val notificationsUseCase: GetUserNotificationsUseCase
) {
    suspend operator fun invoke(): Result<UserInfo> {
        return userInfoUseCase().fold(
            onFailure = { throwable -> Result.failure(throwable) },
            onSuccess = { userInfo ->
                notificationsUseCase().fold(
                    onFailure = { throwable -> Result.failure(throwable) },
                    onSuccess = { notificationsResponse ->
                        val evenIdNotifications = notificationsResponse.data.filter { data ->
                            data.id.last().toString().toIntOrNull()?.let { it % 2 == 0 } ?: false
                        }
                        val updatedUserInfo = userInfo.copy(notifications = evenIdNotifications)
                        Result.success(updatedUserInfo)
                    }
                )
            }
        )
    }
}