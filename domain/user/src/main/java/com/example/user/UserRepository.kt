package com.example.user


interface UserRepository {
    suspend fun getUserInfo(): Result<UserInfo>
}