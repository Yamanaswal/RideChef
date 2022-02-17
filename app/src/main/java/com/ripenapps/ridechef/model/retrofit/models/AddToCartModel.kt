package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName

class AddToCartRequest(
    @SerializedName("merchant_id")
    var merchantId: String? = null,
    @SerializedName("menu_id")
    var menuId: String? = null ,
    @SerializedName("price")
    var price: String? = null ,
    @SerializedName("menu_sub_variant_id")
    var menuSubVariantId: String? = null,
    @SerializedName("add_ons")
    var addOns: List<AddOns>? = null,
){

}


data class AddOns(
    @SerializedName("id")
    val id: String,
    @SerializedName("price")
    val price: String
) {}

data class AddToCartResponse(
    @SerializedName("data")
    val data: AddToCartResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class AddToCartResponseData(
    @SerializedName("total_cart_item")
    val totalCartItem: Int
)


