package com.example.auth

interface AuthRepository {
    suspend fun getToken(): Result<Token>
}