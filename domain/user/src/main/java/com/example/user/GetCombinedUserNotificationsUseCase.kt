package com.example.user

import com.example.notifications.GetUserNotificationsUseCase
import com.example.notifications.NotificationsResponse

class GetCombinedUserNotificationsUseCase(
    private val userInfoUseCase: GetUserInfoUseCase,
    private val notificationsUseCase: GetUserNotificationsUseCase
) {
    suspend operator fun invoke(isFiltered: Boolean = false): Result<UserInfo> {
        return userInfoUseCase().fold(
            onFailure = { throwable -> Result.failure(throwable) },
            onSuccess = { userInfo ->
                notificationsUseCase().fold(
                    onFailure = { throwable -> Result.failure(throwable) },
                    onSuccess = { notificationsResponse ->
                        val evenIdNotifications = notificationsResponse.data.filter { data ->
                            data.id.last().toString().toIntOrNull()?.let { it % 2 == 0 } ?: false
                        }
                        val updatedUserInfo = userInfo.copy(
                            notifications = NotificationsResponse(
                                count = notificationsResponse.count,
                                page = notificationsResponse.page,
                                perPage = notificationsResponse.perPage,
                                channel = notificationsResponse.channel,
                                data = if (isFiltered) evenIdNotifications else notificationsResponse.data
                            )
                        )
                        Result.success(updatedUserInfo)
                    }
                )
            }
        )
    }
}