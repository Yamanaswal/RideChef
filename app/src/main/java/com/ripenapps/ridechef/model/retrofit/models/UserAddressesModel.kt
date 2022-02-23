package com.ripenapps.ridechef.model.retrofit.models
import com.google.gson.annotations.SerializedName


data class MakeDefaultAddressRequest(
    @SerializedName("user_address_id")
    val userAddressId: String
)

data class SaveUserAddressRequest(
    @SerializedName("address")
    val address: String,
    @SerializedName("default")
    val default: String,
    @SerializedName("full_address")
    val fullAddress: String,
    @SerializedName("landmark")
    val landmark: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("pincode")
    val pinCode: String,
    @SerializedName("request_type")
    val requestType: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("user_address_id")
    val userAddressId: String
)

data class UserAddressesResponse(
    @SerializedName("data")
    val data: List<UserAddressesData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class UserAddressesData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("default")
    val default: Int,
    @SerializedName("deleted_at")
    val deletedAt: Any?,
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
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
)