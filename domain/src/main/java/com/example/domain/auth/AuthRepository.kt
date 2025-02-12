package com.example.domain.auth

import com.example.domain.token.Token

interface AuthRepository {
    suspend fun getToken(code: String): Result<Token?>
}