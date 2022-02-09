package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName

data class EmptyResponse(

    @field:SerializedName(value = "status")
    val status: Int = 0,

    @field:SerializedName("message")
    val message: String = "",

    @field:SerializedName("data")
    val data: EmptyResponseData? = EmptyResponseData()

)

class EmptyResponseData {}
