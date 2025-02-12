package com.example.data.user

import com.example.data.BOApi
import com.example.domain.notifications.NotificationsResponse
import com.example.domain.user.User
import com.example.domain.user.UserRepository

class UserRepositoryImpl(
    private val api: BOApi
) : UserRepository {
    override suspend fun getUserInfo(): Result<User> {
        return try {
            val response = api.getUserInfo()
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

    override suspend fun getUsersNotifications(): Result<NotificationsResponse> {
        TODO("Not yet implemented")
    }

}