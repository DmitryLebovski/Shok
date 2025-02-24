package com.example.notifications

import com.google.gson.annotations.SerializedName

data class NotificationsResponseDto (
    @SerializedName("count"   ) var count   : Int?,
    @SerializedName("page"    ) var page    : Int?,
    @SerializedName("perPage" ) var perPage : Int?,
    @SerializedName("channel" ) var channel : String?,
    @SerializedName("data"    ) var data    : List<DataDto>
)

data class DataDto (
    @SerializedName("date"           ) var date           : String?,
    @SerializedName("unread"         ) var unread         : Boolean?,
    @SerializedName("requestId"      ) var requestId      : String?,
    @SerializedName("projectId"      ) var projectId      : String?,
    @SerializedName("projectName"    ) var projectName    : String?,
    @SerializedName("requestNumber"  ) var requestNumber  : Int?,
    @SerializedName("requestSubject" ) var requestSubject : String?,
    @SerializedName("content"        ) var content        : String?,
    @SerializedName("requestTypeId"  ) var requestTypeId  : String?,
    @SerializedName("typeId"         ) var typeId         : String?,
    @SerializedName("type"           ) var type           : String?,
    @SerializedName("id"             ) var id             : String?,
    @SerializedName("urgent"         ) var urgent         : Boolean?,
    @SerializedName("initiator"      ) var initiator      : String?
)

fun DataDto.toDomain(): Data {
    return Data(
        date = date ?: "empty",
        unread = unread ?: false,
        requestId = requestId ?: "empty",
        projectId = projectId ?: "empty",
        projectName = projectName ?: "empty",
        requestNumber = requestNumber ?: 0,
        requestSubject = requestSubject ?: "empty",
        content = content ?: "empty",
        requestTypeId = requestTypeId ?: "empty",
        typeId = typeId ?: "empty",
        type = typeId ?: "empty",
        id = id ?: "empty",
        urgent = urgent ?: false,
        initiator = initiator ?: "empty"
    )
}

fun NotificationsResponseDto.toDomain(): NotificationsResponse {
    return NotificationsResponse(
        count = count ?: 0,
        page = page ?: 0,
        perPage = perPage ?: 0,
        channel = channel ?: "empty",
        data = data.map { it.toDomain() }
    )
}