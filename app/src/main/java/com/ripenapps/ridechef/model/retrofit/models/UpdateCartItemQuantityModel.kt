package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName


data class UpdateCartItemQuantityRequest(
    @SerializedName("price")
    val price: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("user_cart_id")
    val userCartId: Int
)


data class UpdateCartItemQuantityResponse(
    @SerializedName("data")
    val data: UpdateCartItemQuantityResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class UpdateCartItemQuantityResponseData(
    @SerializedName("added_quantity")
    val addedQuantity: Int
)