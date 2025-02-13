package com.example.notifications

import com.google.gson.annotations.SerializedName

data class NotificationsResponse (
    @SerializedName("count"   ) var count   : Int?,
    @SerializedName("page"    ) var page    : Int?,
    @SerializedName("perPage" ) var perPage : Int?,
    @SerializedName("channel" ) var channel : String?,
    @SerializedName("data"    ) var data    : List<Data>
)

data class Data (
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
