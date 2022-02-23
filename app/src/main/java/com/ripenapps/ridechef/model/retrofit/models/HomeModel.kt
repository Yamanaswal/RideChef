package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName


data class HomeRequest(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("user_id")
    val userId: String,
)

data class HomeResponse(
    @SerializedName("data")
    val data: HomeResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class HomeResponseData(
    @SerializedName("adminBanner")
    val adminBanner: List<AdminBanner>,
    @SerializedName("cuisines")
    val cuisines: List<Cuisines>,
    @SerializedName("featuredRestaurant")
    val featuredRestaurant: List<Restaurant>,
    @SerializedName("trendingdRestaurant")
    val trendingRestaurant: List<Restaurant>,
    @SerializedName("cartInfo")
    val cartInfo: CartInfo?
)

data class CartInfo(
    @SerializedName("restaurant_name")
    val restaurantName: String,
    @SerializedName("image")
    val image: String
)

data class AdminBanner(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)

data class Cuisines(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_trending")
    val isTrending: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class Restaurant(
    @SerializedName("average_price")
    val averagePrice: String,
    @SerializedName("distance")
    val distance: String,
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
)

data class MerchantCusine(
    @SerializedName("cuisine")
    val cuisine: Cuisine,
    @SerializedName("cuisine_id")
    val cuisineId: Int,
    @SerializedName("merchant_id")
    val merchantId: Int
)

data class RestaurantImage(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("merchant_id")
    val merchantId: Int,
    @SerializedName("status")
    val status: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class Cuisine(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)


