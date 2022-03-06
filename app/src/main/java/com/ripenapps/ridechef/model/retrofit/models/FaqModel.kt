package com.ripenapps.ridechef.model.retrofit.models

import com.google.gson.annotations.SerializedName


data class FaqRequest(
    @SerializedName("search")
    var search: String
)


data class FaqResponse(
    @SerializedName("data")
    val data: FaqResponseData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class FaqResponseData(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val data: List<FaqResponseDataData>,
    @SerializedName("first_page_url")
    val firstPageUrl: String,
    @SerializedName("from")
    val from: Int,
    @SerializedName("last_page")
    val lastPage: Int,
    @SerializedName("last_page_url")
    val lastPageUrl: String,
    @SerializedName("next_page_url")
    val nextPageUrl: String,
    @SerializedName("path")
    val path: String,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("prev_page_url")
    val prevPageUrl: String,
    @SerializedName("to")
    val to: Int,
    @SerializedName("total")
    val total: Int
)

data class FaqResponseDataData(
    @SerializedName("answer")
    val answer: String,
    @SerializedName("question")
    val question: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    var isOpen: Boolean = false
)
