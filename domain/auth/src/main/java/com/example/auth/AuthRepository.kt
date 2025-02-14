package com.example.auth

interface AuthRepository {
    suspend fun getTokenFromApi(code: String): Result<Token?>
    suspend fun getToken(): Token?
    suspend fun saveToken(token: Token)
}