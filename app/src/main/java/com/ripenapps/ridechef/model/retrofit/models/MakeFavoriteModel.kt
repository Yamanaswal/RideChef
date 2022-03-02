package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName

data class MakeFavoriteRequest(
    @SerializedName("merchant_id")
    val merchantId: Int
)


