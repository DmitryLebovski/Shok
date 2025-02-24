package com.example.user

import com.example.error.GlobalErrorHandler
import com.example.error.ResponseHandler
import retrofit2.Retrofit

class UserRepositoryImpl(
    private val userRetrofit: Retrofit
) : UserRepository {

    private val userApi: UserApi by lazy {
        userRetrofit.create(UserApi::class.java)
    }

    override suspend fun getUserInfo(token: String): Result<UserInfo> {
        return try {
            val response = userApi.getUserInfo("Bearer $token")
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