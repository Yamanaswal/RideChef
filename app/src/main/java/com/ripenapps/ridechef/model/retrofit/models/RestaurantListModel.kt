package com.ripenapps.ridechef.model.retrofit.models
import com.google.gson.annotations.SerializedName


data class RestaurantListRequest(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("search")
    val search: String,
    @SerializedName("cuisine_id")
    val cuisine_id: String
) {}


data class RestaurantListResponse(
    @SerializedName("data")
    val data: RestaurantListResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class RestaurantListResponseData(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val data: List<RestaurantListResponseDataData>,
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

data class RestaurantListResponseDataData(
    @SerializedName("average_price")
    val averagePrice: String,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("merchant_cusines")
    val merchantCusines: List<MerchantCusine>,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("restaurant_images")
    val restaurantImages: List<RestaurantImage>,
    @SerializedName("restaurant_name")
    val restaurantName: String,
    @SerializedName("shop_location")
    val shopLocation: String
)

