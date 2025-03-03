package com.example.user

import com.google.gson.annotations.SerializedName

data class UserInfoDto (
    @SerializedName("isClient"                 ) var isClient                 : Boolean?,
    @SerializedName("guid"                     ) var guid                     : String?,
    @SerializedName("language"                 ) var language                 : String?,
    @SerializedName("version"                  ) var version                  : Int?,
    @SerializedName("userName"                 ) var userName                 : String?,
    @SerializedName("email"                    ) var email                    : String?,
    @SerializedName("noSMS"                    ) var noSMS                    : Boolean?,
    @SerializedName("allowedContentExtensions" ) var allowedContentExtensions : String?,
    @SerializedName("allowedContentSize"       ) var allowedContentSize       : Int?,
    @SerializedName("dashboardAccess"          ) var dashboardAccess          : Boolean?,
    @SerializedName("hasAccessToAccount"       ) var hasAccessToAccount       : Boolean?,
    @SerializedName("unpaidInvoicesCount"      ) var unpaidInvoicesCount      : Int?,
    @SerializedName("requestSectionId"         ) var requestSectionId         : String?
)

fun UserInfoDto.toDomain(): UserInfo {
    return UserInfo(
        isClient = isClient ?: false,
        guid = guid ?: "empty",
        language = language ?: "empty",
        version = version ?: 0,
        userName = userName ?: "empty",
        email = email ?: "empty",
        noSMS = noSMS ?: false,
        allowedContentExtensions = allowedContentExtensions ?: "empty",
        allowedContentSize = allowedContentSize ?: 0,
        dashboardAccess = dashboardAccess ?: false,
        hasAccessToAccount = hasAccessToAccount ?: false,
        unpaidInvoicesCount = unpaidInvoicesCount ?: 0,
        requestSectionId = requestSectionId ?: "empty",
        notifications = emptyList()
    )
}