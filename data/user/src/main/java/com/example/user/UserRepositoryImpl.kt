package com.example.user

import com.example.auth.AuthRepository
import com.example.error.NetworkErrorHandler
import com.example.error.ResponseHandler
import com.example.settings.NetworkSettings

class UserRepositoryImpl(
    private val settings: NetworkSettings,
    private val authRepository: AuthRepository
) : UserRepository {

    private val userApi: UserApi by lazy { UserNetworking.getUserApi(settings, authRepository) }

    override suspend fun getUserInfo(): Result<UserInfo> {
        return try {
            val response = userApi.getUserInfo()
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