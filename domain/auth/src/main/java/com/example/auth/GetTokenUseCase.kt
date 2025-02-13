package com.example.auth

import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(code: String): Result<Token?> {
        return authRepository.getToken(code = code)
    }
}