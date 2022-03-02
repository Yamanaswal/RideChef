package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName


data class EmptyResponse(

    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: EmptyResponseData = EmptyResponseData()

)

class EmptyResponseData {}
