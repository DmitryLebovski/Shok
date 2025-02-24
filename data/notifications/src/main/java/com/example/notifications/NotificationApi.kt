package com.example.notifications

import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface NotificationApi {
    @POST("api/web/v2/notifications")
    suspend fun getUserNotifications(
        @Query("channel") channel: String = "web",
        @Query("page") page: Int = 1,
        @Query("perPage") perPage: Int = 20,
        @Query("unread") unread: Boolean = true,
        @Header("Authorization") authorization: String
    ): Response<NotificationsResponseDto>
}