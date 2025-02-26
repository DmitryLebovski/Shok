package com.example.auth

interface AuthRepository {
    suspend fun getToken(code: String = "placeholder"): Result<Token>
}