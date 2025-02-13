package com.example.notifications

class NotificationsRepositoryImpl(
    private val api: NotificationApi
) : NotificationsRepository {
    override suspend fun getUsersNotifications(): Result<NotificationsResponse> {
        return try {
            val response = api.getUserNotifications()
            println(response.toString())
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Throwable(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
