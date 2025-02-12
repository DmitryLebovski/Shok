package com.example.domain.use_cases

import com.example.domain.user.User
import com.example.domain.user.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<User> {
        return userRepository.getUserInfo()
    }
}