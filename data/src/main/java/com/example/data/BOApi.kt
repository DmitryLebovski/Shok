package com.example.data

import com.example.domain.notifications.NotificationsResponse
import com.example.domain.user.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BOApi {
    @GET("ReactWeb/user_info")
    suspend fun getUserInfo(
    ): Response<User>

    @POST("api/web/v2/notifications")
    suspend fun getUserNotifications(
        @Query("channel") channel: String = "web",
        @Query("page") page: Int = 1,
        @Query("perPage") perPage: Int = 20,
        @Query("unread") unread: Boolean = true,
    ): Response<NotificationsResponse>
}
