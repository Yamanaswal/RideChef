package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName

data class RateDriverMerchantRequest(
    @SerializedName("driver_id")
    val driverId: String,
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("type")
    val type: String
)




