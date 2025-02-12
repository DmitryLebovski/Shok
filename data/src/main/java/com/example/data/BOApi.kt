package com.example.data

import com.example.domain.user.User
import retrofit2.Response
import retrofit2.http.GET

interface BOApi {
    @GET("ReactWeb/user_info")
    suspend fun getUserInfo(
    ): Response<User>
}
