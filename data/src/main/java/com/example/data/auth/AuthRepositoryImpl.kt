package com.example.data.auth

import com.example.domain.auth.AuthRepository

class AuthRepositoryImpl(
): AuthRepository {
    override suspend fun getToken(): String? {
        TODO("Not yet implemented")
    }
}