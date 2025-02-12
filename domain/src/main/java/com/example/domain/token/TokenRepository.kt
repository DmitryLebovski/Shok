package com.example.domain.token

interface TokenRepository {
    suspend fun getToken(): Token?
    suspend fun saveToken(token: Token)
}