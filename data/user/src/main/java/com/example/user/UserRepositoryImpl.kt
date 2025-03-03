package com.example.user

import com.example.auth.AuthRepository
import com.example.error.NetworkErrorHandler
import com.example.error.ResponseHandler
import com.example.settings.NetworkSettings
import java.util.concurrent.atomic.AtomicReference

class UserRepositoryImpl(
    private val settings: NetworkSettings,
    private val authRepository: AuthRepository
) : UserRepository {

    private val userApi: UserApi by lazy { UserNetworking.getUserApi(settings, authRepository) }
    private var cachedUser: AtomicReference<UserInfo?> = AtomicReference(null)


    override suspend fun getUserInfo(): Result<UserInfo> {
        val currentUser = cachedUser.get()

        return if (!currentUser?.userName.isNullOrEmpty()) {
            Result.success(currentUser!!)
        } else {
            try {
                val response = userApi.getUserInfo()
                if (response.isSuccessful) {
                    val newUser = response.body()!!.toDomain()
                    cachedUser.set(newUser)
                    Result.success(newUser)
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