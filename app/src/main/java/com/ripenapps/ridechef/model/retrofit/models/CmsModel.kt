package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName


data class CmsRequest(
    @SerializedName("type")
    val type: Int
)

data class CmsResponse(
    @SerializedName("data")
    val data: CmsResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class CmsResponseData(
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: Int
)