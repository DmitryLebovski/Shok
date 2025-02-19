package com.example.notifications
data class NotificationsResponse (
    val count: Int,
    val page: Int,
    val perPage: Int,
    val channel: String,
    val data: List<Data>
)

data class Data (
    val date: String,
    val unread: Boolean,
    val requestId: String,
    val projectId: String,
    val projectName: String,
    val requestNumber: Int,
    val requestSubject: String,
    val content: String,
    val requestTypeId: String,
    val typeId: String,
    val type: String,
    val id: String,
    val urgent: Boolean,
    val initiator: String
)
