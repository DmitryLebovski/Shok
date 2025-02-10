package com.example.domain.auth

interface AuthRepository {
    suspend fun getToken(): String?
}