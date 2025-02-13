package com.example.auth

import com.google.gson.annotations.SerializedName

data class Token (
    @SerializedName("access_token") val accessToken: String?
)