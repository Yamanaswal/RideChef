package com.ripenapps.ridechef.model.retrofit.models
import com.google.gson.annotations.SerializedName


data class MyFavouriteResponse(
    @SerializedName("data")
    val data: List<MyFavouriteResponseData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class MyFavouriteResponseData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("favorite_merchant")
    val favoriteMerchant: FavoriteMerchant,
    @SerializedName("id")
    val id: Int,
    @SerializedName("merchant_id")
    val merchantId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
)

data class FavoriteMerchant(
    @SerializedName("average_price")
    val averagePrice: String,
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
    @SerializedName("restaurant_address")
    val restaurantAddress: String,
    @SerializedName("restaurant_images")
    val restaurantImages: List<RestaurantImage>,
    @SerializedName("restaurant_name")
    val restaurantName: String,
    @SerializedName("shop_location")
    val shopLocation: String
)
