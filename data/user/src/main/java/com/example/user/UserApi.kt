package com.example.user

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {
    @GET("ReactWeb/user_info")
    suspend fun getUserInfo(
        @Header("Authorization") authorization: String
    ): Response<UserInfoDto>
}
