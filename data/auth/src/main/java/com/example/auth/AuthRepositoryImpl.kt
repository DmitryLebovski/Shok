package com.example.auth

class AuthRepositoryImpl(
    private val api: AuthApi
): AuthRepository {
    override suspend fun getToken(code: String): Result<Token?> {
        return try {
            val response = api.postTokenByCode(code = code)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Throwable(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}