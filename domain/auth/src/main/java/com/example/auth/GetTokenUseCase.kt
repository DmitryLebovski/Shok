package com.example.auth

import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(code: String): Result<Token> { // использовать use case для других use case, repository в оперативке хранит token
        return authRepository.getToken(code = code)
    }
}

//в основном юз кейсе на запрос токена - делаю запрос к репозиторию,