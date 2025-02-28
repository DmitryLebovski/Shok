package com.example.auth

import com.example.error.NetworkErrorHandler
import com.example.error.ResponseHandler
import java.util.concurrent.atomic.AtomicReference
import com.example.settings.NetworkSettings

class AuthRepositoryImpl(
    private val settings: NetworkSettings
): AuthRepository {

    private val authApi: AuthApi by lazy { AuthNetworking.getAuthApi(settings) }
    private var cachedToken: AtomicReference<Token?> = AtomicReference(null)

    override suspend fun getToken(): Result<Token> {
        val currentToken = cachedToken.get()
        val code = AuthCodeHolder.code

        return if (!currentToken?.accessToken.isNullOrEmpty()) {
            Result.success(currentToken!!)
        } else {
            try {
                val response = authApi.postTokenByCode(code = code.toString())
                if (response.isSuccessful) {
                    val newToken = response.body()!!.toDomain()
                    cachedToken.set(newToken)
                    AuthCodeHolder.clearCode()
                    Result.success(newToken)
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