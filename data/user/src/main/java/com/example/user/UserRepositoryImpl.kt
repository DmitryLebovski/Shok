package com.example.user

class UserRepositoryImpl(
    private val api: UserApi
) : UserRepository {
    override suspend fun getUserInfo(): Result<UserInfo> {
        return try {
            val response = api.getUserInfo()
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