package com.example.data

import com.example.domain.auth.Token
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BOApi {
    @FormUrlEncoded
    @POST("ReactWeb/user_info")
    suspend fun getUserInfo(
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("client_id") clientId: String = "onec_client",
        @Field("scope") scope: String = "openid",
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String = "bellerage://mobilelogin"
    ): Response<Token>
}
