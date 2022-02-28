package com.ripenapps.ridechef.model.retrofit.models
import com.google.gson.annotations.SerializedName


data class UpdateUserProfileRequest(
    @SerializedName("dob")
    var dob: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("profile_image")
    var profileImage: String
)

data class GetUserProfileResponse(
    @SerializedName("data")
    val data: GetUserProfileResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class GetUserProfileResponseData(
    @SerializedName("address")
    val address: String,
    @SerializedName("device_type")
    val deviceType: Int,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fcm_token")
    val fcmToken: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("mobile_number")
    val mobileNumber: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("status")
    val status: Int
)