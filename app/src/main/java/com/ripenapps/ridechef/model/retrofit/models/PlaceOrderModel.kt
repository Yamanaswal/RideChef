package com.ripenapps.ridechef.model.retrofit.models
import com.google.gson.annotations.SerializedName


data class PlaceOrderRequest(
    @SerializedName("address_id")
    val addressId: String,
    @SerializedName("card_no")
    val cardNo: String,
    @SerializedName("cvv")
    val cvv: String,
    @SerializedName("delivery_charge")
    val deliveryCharge: String,
    @SerializedName("expiry")
    val expiry: String,
    @SerializedName("final_price")
    val finalPrice: String,
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("payment_type")
    val paymentType: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("rider_tip")
    val riderTip: String,
    @SerializedName("source_id")
    val sourceId: String,
    @SerializedName("tax")
    val tax: String
)


