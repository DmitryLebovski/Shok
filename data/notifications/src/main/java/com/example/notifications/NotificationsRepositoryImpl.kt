package com.example.notifications

import com.example.auth.AuthRepository
import com.example.error.NetworkErrorHandler
import com.example.error.ResponseHandler
import com.example.settings.NetworkSettings

class NotificationsRepositoryImpl(
    private val settings: NetworkSettings,
    private val authRepository: AuthRepository
) : NotificationsRepository {

    private val notificationApi: NotificationApi by lazy { NotificationNetworking.getNotificationApi(settings, authRepository) }

    override suspend fun getUsersNotifications(): Result<NotificationsResponse> {
        return try {
            val response = notificationApi.getUserNotifications()
            println(response.toString())
            if (response.isSuccessful) {
                Result.success(response.body()!!.toDomain())
            } else {
                val error = ResponseHandler.handleResponse(response)
                NetworkErrorHandler.emitError(error)
                Result.failure(error)
            }
        } catch (e: Exception) {
            NetworkErrorHandler.emitError(e)
            Result.failure(e)
        }
    }
}
