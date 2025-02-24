package com.example.notifications

import com.example.error.GlobalErrorHandler
import com.example.error.ResponseHandler
import retrofit2.Retrofit

class NotificationsRepositoryImpl(
    private val notificationRetrofit: Retrofit
) : NotificationsRepository {

    private val notificationApi: NotificationApi by lazy {
        notificationRetrofit.create(NotificationApi::class.java)
    }

    override suspend fun getUsersNotifications(token: String): Result<NotificationsResponse> {
        return try {
            val response = notificationApi.getUserNotifications(authorization = "Bearer $token")
            println(response.toString())
            if (response.isSuccessful) {
                Result.success(response.body()!!.toDomain())
            } else {
                val error = ResponseHandler.handleResponse(response)
                GlobalErrorHandler.emitError(error)
                Result.failure(error)
            }
        } catch (e: Exception) {
            GlobalErrorHandler.emitError(e)
            Result.failure(e)
        }
    }
}
