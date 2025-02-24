package com.example.auth

import com.google.gson.annotations.SerializedName

data class TokenDto (
    @SerializedName("access_token") val accessToken: String?
)

fun TokenDto.toDomain(): Token {
    return Token(
        accessToken = accessToken ?: "empty"
    )
}