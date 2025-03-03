package com.example.user

import com.example.notifications.Data

data class UserInfo (
    val isClient: Boolean,
    val guid: String,
    val language: String,
    val version: Int,
    val userName: String,
    val email: String,
    val noSMS: Boolean,
    val allowedContentExtensions : String,
    val allowedContentSize: Int,
    val dashboardAccess: Boolean,
    val hasAccessToAccount: Boolean,
    val unpaidInvoicesCount: Int,
    val requestSectionId: String,

    val notifications: List<Data>
)
