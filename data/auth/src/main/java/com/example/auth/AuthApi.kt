package com.example.auth

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("realms/bellerage/protocol/openid-connect/token")
    suspend fun postTokenByCode(
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("client_id") clientId: String = "onec_client",
        @Field("scope") scope: String = "openid",
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String = "bellerage://mobilelogin",
    ): Response<TokenDto>
}