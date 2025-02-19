package com.example.auth

interface ProviderAuthUtils {
    fun tokenUseCase(): GetTokenUseCase
    fun authRepository(): AuthRepository
}