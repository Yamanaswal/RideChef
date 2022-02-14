package com.ripenapps.ridechef.model.retrofit.models
import com.google.gson.annotations.SerializedName


data class RestaurantDetailsRequest(
    @SerializedName("menu_type")
    val menuType: Int,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("search")
    val search: String,
    @SerializedName("veg_type")
    val vegType: Int
)

data class RestaurantDetailsResponse(
    @SerializedName("data")
    val data: RestaurantDetailsResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class RestaurantDetailsResponseData(
    @SerializedName("average_price")
    val averagePrice: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("merchant_menu_types")
    val merchantMenuTypes: List<MerchantMenuType>,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("restaurant_name")
    val restaurantName: String,
    @SerializedName("shop_location")
    val shopLocation: String
)

data class MerchantMenuType(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("menus")
    val menus: List<Menu>,
    @SerializedName("merchant_id")
    val merchantId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class Menu(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("final_price")
    val finalPrice: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_available")
    val isAvailable: Int,
    @SerializedName("menu_type_id")
    val menuTypeId: Int,
    @SerializedName("merchant_id")
    val merchantId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("type")
    val type: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)