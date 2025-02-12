package com.example.domain.user

import com.google.gson.annotations.SerializedName

data class User (
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
