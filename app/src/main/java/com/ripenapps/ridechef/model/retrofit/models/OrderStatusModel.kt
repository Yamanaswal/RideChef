package com.ripenapps.ridechef.model.retrofit.models
import com.google.gson.annotations.SerializedName


data class OrderStatusDetailsRequest(
    @SerializedName("order_id")
    val orderId: Int
)

data class OrderStatusDetailsResponse(
    @SerializedName("data")
    val data: OrderStatusDetailsResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class OrderStatusDetailsResponseData(
    @SerializedName("address_id")
    val addressId: Int,
    @SerializedName("assigned_driver_id")
    val assignedDriverId: Int,
    @SerializedName("coupon_discount")
    val couponDiscount: Any?,
    @SerializedName("delivery_address")
    val deliveryAddress: DeliveryAddress,
    @SerializedName("delivery_charge")
    val deliveryCharge: String,
    @SerializedName("driver_details")
    val driverDetails: DriverDetails,
    @SerializedName("driver_rating")
    val driverRating: DriverRating,
    @SerializedName("final_price")
    val finalPrice: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("merchant_id")
    val merchantId: Int,
    @SerializedName("merchant_rating")
    val merchantRating: MerchantRating,
    @SerializedName("order_items")
    val orderItems: List<OrderItem>,
    @SerializedName("order_status")
    val orderStatus: Int,
    @SerializedName("otp")
    val otp: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("restaurant_details")
    val restaurantDetails: RestaurantDetails,
    @SerializedName("rider_tip")
    val riderTip: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("tax")
    val tax: String,
    @SerializedName("user_id")
    val userId: Int
)

data class DeliveryAddress(
    @SerializedName("full_address")
    val fullAddress: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("landmark")
    val landmark: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("location_on_map")
    val locationOnMap: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("pincode")
    val pincode: Int,
    @SerializedName("type")
    val type: Int,
    @SerializedName("user_id")
    val userId: Int
)

data class DriverDetails(
    @SerializedName("captian_name")
    val captianName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("mobile_number")
    val mobileNumber: String
)

data class DriverRating(
    @SerializedName("id")
    val id: Int,
    @SerializedName("order_id")
    val orderId: Int,
    @SerializedName("rating")
    val rating: Int
)

data class MerchantRating(
    @SerializedName("id")
    val id: Int,
    @SerializedName("order_id")
    val orderId: Int,
    @SerializedName("rating")
    val rating: Int
)

data class OrderItem(
    @SerializedName("add_ons")
    val addOns: List<Addon>,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("menu_details")
    val menuDetails: MenuDetails,
    @SerializedName("menu_id")
    val menuId: Int,
    @SerializedName("menu_sub_variants_id")
    val menuSubVariantsId: String,
    @SerializedName("menu_variant_details")
    val menuVariantDetails: MenuVariantDetails?,
    @SerializedName("order_id")
    val orderId: Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class RestaurantDetails(
    @SerializedName("city_id")
    val cityId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("pincode")
    val pincode: Int,
    @SerializedName("restaurant_address")
    val restaurantAddress: String,
    @SerializedName("restaurant_images")
    val restaurantImages: List<RestaurantImage>,
    @SerializedName("restaurant_name")
    val restaurantName: String,
    @SerializedName("shop_location")
    val shopLocation: String,
    @SerializedName("state_id")
    val stateId: Int
)

data class MenuDetails(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deleted_at")
    val deletedAt: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("discount")
    val discount: Any?,
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

data class MenuVariantDetails(
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