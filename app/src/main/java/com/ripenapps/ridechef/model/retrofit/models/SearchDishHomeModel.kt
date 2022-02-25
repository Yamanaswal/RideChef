package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName


data class SearchHomeRequest(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("search")
    val search: String
)


data class SearchDishHomeResponse(
    @SerializedName("data")
    val data: SearchDishHomeResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class SearchDishHomeResponseData(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val data: List<SearchDishHomeResponseDataData>,
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
    val total: Int,
    @SerializedName("data")
    val dataInsideRest: List<MerchantMenu>,
)

data class SearchDishHomeResponseDataData(
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
    @SerializedName("menu_type")
    val menuType: MenuType,
    @SerializedName("menu_type_id")
    val menuTypeId: Int,
    @SerializedName("merchant_id")
    val merchantId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String
)

data class MenuType(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("merchant_id")
    val merchantId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class SearchDishInsideRestRequest(
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("search")
    val search: String
)