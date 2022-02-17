package com.ripenapps.ridechef.model.retrofit.models
import com.google.gson.annotations.SerializedName


data class CartDetailsResponse(
    @SerializedName("data")
    val data: CartDetailsResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class CartDetailsResponseData(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("restaurant_details")
    val restaurantDetails: RestaurantDetails,
    @SerializedName("shipping_charge")
    val shippingCharge: Int,
    @SerializedName("total_amount")
    val totalAmount: Int
)

data class Item(
    @SerializedName("add_ons")
    val addOns: List<AddOnsList>,
    @SerializedName("add_ons_sum_price")
    val addOnsSumPrice: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("menu_details")
    val menuDetails: MenuDetails,
    @SerializedName("menu_id")
    val menuId: Int,
    @SerializedName("menu_sub_variant_id")
    val menuSubVariantId: Int?,
    @SerializedName("merchant_id")
    val merchantId: Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("variant")
    val variant: Variant?
)

data class RestaurantDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
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

data class AddOnsList(
    @SerializedName("addon")
    val addon: Addon,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("menu_sub_variant_id")
    val menuSubVariantId: Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_cart_id")
    val userCartId: Int,
    @SerializedName("user_id")
    val userId: Int
)

data class MenuDetails(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deleted_at")
    val deletedAt: Any?,
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

data class Variant(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deleted_at")
    val deletedAt: Any?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Any?,
    @SerializedName("is_default")
    val isDefault: Int,
    @SerializedName("menu_id")
    val menuId: Int,
    @SerializedName("menu_variant_id")
    val menuVariantId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class Addon(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deleted_at")
    val deletedAt: Any?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Any?,
    @SerializedName("is_default")
    val isDefault: Int,
    @SerializedName("menu_id")
    val menuId: Int,
    @SerializedName("menu_variant_id")
    val menuVariantId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("updated_at")
    val updatedAt: String
)
