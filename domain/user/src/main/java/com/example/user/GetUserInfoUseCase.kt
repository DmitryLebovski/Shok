package com.example.user

import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(token: String): Result<UserInfo> {
        return userRepository.getUserInfo(token = token)
    }
}