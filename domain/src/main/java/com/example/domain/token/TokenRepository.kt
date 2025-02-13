package com.example.domain.token

import com.example.auth.Token

interface TokenRepository {
    suspend fun getToken(): Token?
    suspend fun saveToken(token: Token)
}