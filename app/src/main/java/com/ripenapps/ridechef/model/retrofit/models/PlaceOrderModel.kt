package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName


data class PlaceOrderRequest(
    @SerializedName("address_id")
    val addressId: String,
    @SerializedName("delivery_charge")
    val deliveryCharge: String,
    @SerializedName("final_price")
    val finalPrice: String,
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("payment_method")
    val paymentMethod: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("rider_tip")
    val riderTip: String,
    @SerializedName("tax")
    val tax: String
)


data class PlaceOrderResponse(
    @SerializedName("data")
    val data: PlaceOrderResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class PlaceOrderResponseData(
    @SerializedName("order_id")
    val orderId: Int
)