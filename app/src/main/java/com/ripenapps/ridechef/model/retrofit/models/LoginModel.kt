package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("address")
    val address: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("mobile_number")
    val mobileNumber: String
)


data class LoginResponse(
    @SerializedName("data")
    val data: LoginResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class LoginResponseData(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("expire_at")
    val expireAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("mobile_number")
    val mobileNumber: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("updated_at")
    val updatedAt: String
)


