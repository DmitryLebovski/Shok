package com.example.notifications

import com.example.auth.AuthRepository
import com.example.error.NetworkErrorHandler
import com.example.error.ResponseHandler
import com.example.settings.NetworkSettings
import java.util.concurrent.atomic.AtomicReference

class NotificationsRepositoryImpl(
    private val settings: NetworkSettings,
    private val authRepository: AuthRepository
) : NotificationsRepository {

    private val notificationApi: NotificationApi by lazy { NotificationNetworking.getNotificationApi(settings, authRepository) }
    private var cachedNotifications: AtomicReference<NotificationsResponse?> = AtomicReference(null)

    override suspend fun getUsersNotifications(): Result<NotificationsResponse> {
        val currentNotifications = cachedNotifications.get()

        return if (!currentNotifications?.data.isNullOrEmpty()) {
            Result.success(currentNotifications!!)
        } else {
            try {
                val response = notificationApi.getUserNotifications()
                println(response.toString())
                if (response.isSuccessful) {
                    val newNotifications = response.body()!!.toDomain()
                    cachedNotifications.set(newNotifications)
                    Result.success(newNotifications)
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
}
