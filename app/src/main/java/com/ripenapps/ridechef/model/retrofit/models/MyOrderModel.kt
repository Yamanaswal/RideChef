package com.ripenapps.ridechef.model.retrofit.models
import com.google.gson.annotations.SerializedName


data class MyOrderResponse(
    @SerializedName("data")
    val data: MyOrderResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class MyOrderResponseData(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val data: List<MyOrderResponseDataData>,
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

data class MyOrderResponseDataData(
    @SerializedName("address_id")
    val addressId: Int,
    @SerializedName("assigned_driver_id")
    val assignedDriverId: Any?,
    @SerializedName("coupon_discount")
    val couponDiscount: Any?,
    @SerializedName("delivery_address")
    val deliveryAddress: DeliveryAddress,
    @SerializedName("delivery_charge")
    val deliveryCharge: String,
    @SerializedName("driver_details")
    val driverDetails: Any?,
    @SerializedName("earning")
    val earning: Int,
    @SerializedName("final_price")
    val finalPrice: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("merchant_id")
    val merchantId: Int,
    @SerializedName("order_items")
    val orderItems: List<OrderItem>,
    @SerializedName("otp")
    val otp: Int,
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

