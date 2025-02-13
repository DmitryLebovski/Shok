package com.example.user

import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("ReactWeb/user_info")
    suspend fun getUserInfo(
    ): Response<UserInfo>
}
