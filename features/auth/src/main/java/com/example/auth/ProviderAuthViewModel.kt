package com.example.auth

interface ProviderAuthViewModel {
    fun tokenUseCase(): GetTokenUseCase
    fun authRepository(): AuthRepository
}