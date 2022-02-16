package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName


data class ViewAllRequest(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("search")
    val search: String,
    @SerializedName("type")
    val type: Int
)

data class ViewAllResponse(
    @SerializedName("data")
    val data: ViewAllResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class ViewAllResponseData(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val data: List<ViewAllResponseDataData>,
    @SerializedName("first_page_url")
    val firstPageUrl: String,
    @SerializedName("from")
    val from: Int,
    @SerializedName("last_page")
    val lastPage: Int,
    @SerializedName("last_page_url")
    val lastPageUrl: String,
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("next_page_url")
    val nextPageUrl: Any?,
    @SerializedName("path")
    val path: String,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("prev_page_url")
    val prevPageUrl: Any?,
    @SerializedName("to")
    val to: Int,
    @SerializedName("total")
    val total: Int
)

data class ViewAllResponseDataData(
    @SerializedName("average_price")
    val averagePrice: String,
    @SerializedName("distance")
    val distance: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("merchant_cusines")
    val merchantCusines: List<MerchantCusine>,
    @SerializedName("restaurant_images")
    val restaurantImages: List<RestaurantImage>,
    @SerializedName("restaurant_name")
    val restaurantName: String,
    @SerializedName("shop_location")
    val shopLocation: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("image")
    val image: String
)

data class Link(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("label")
    val label: String,
    @SerializedName("url")
    val url: Any?
)

