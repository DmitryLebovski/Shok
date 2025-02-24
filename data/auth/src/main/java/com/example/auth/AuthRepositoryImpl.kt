package com.example.auth

import com.example.error.GlobalErrorHandler
import com.example.error.ResponseHandler
import retrofit2.Retrofit

class AuthRepositoryImpl(
    private val authRetrofit: Retrofit
): AuthRepository {

    private val authApi: AuthApi by lazy {
        authRetrofit.create(AuthApi::class.java)
    }

    private var cachedToken: Token? = null

    override suspend fun getToken(code: String): Result<Token> {
        return if (cachedToken != null) {
            Result.success(cachedToken!!)
        } else {
            try {
                val response = authApi.postTokenByCode(code = code)
                if (response.isSuccessful) {
                    cachedToken = response.body()!!.toDomain()
                    Result.success(cachedToken!!)
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
}