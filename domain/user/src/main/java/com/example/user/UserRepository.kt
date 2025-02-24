package com.example.user


interface UserRepository {
    suspend fun getUserInfo(token: String): Result<UserInfo>
}