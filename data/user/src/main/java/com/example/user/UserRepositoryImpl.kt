package com.example.user

import com.example.error.ResponseHandler

class UserRepositoryImpl(
    private val api: UserApi
) : UserRepository {
    override suspend fun getUserInfo(): Result<UserInfo> {
        return try {
            val response = api.getUserInfo()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(ResponseHandler.handleResponse(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}