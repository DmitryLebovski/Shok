package com.example.domain.use_cases

import com.example.domain.auth.AuthRepository
import com.example.domain.token.Token
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(code: String): Result<Token?> {
        return authRepository.getToken(code = code)
    }
}