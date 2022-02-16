package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName

data class AddToCartRequest(
    @SerializedName("price")
    val price: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("user_cart_id")
    val userCartId: Int
)

data class AddToCartResponse(
    @SerializedName("data")
    val data: AddToCartResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class AddToCartResponseData(
    @SerializedName("added_quantity")
    val addedQuantity: Int
)


