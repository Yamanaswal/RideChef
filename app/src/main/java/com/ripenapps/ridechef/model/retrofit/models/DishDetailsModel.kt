package com.ripenapps.ridechef.model.retrofit.models
import com.google.gson.annotations.SerializedName


data class DishDetailsRequest(
    @SerializedName("menu_id")
    val menuId: Int,
    @SerializedName("merchant_id")
    val merchantId: Int
)


data class DishDetailsResponse(
    @SerializedName("data")
    val data: List<DishDetailsResponseData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class DishDetailsResponseData(
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
    @SerializedName("menu_variants")
    val menuVariants: List<MenuVariant>,
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

data class MenuVariant(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deleted_at")
    val deletedAt: Any?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("menu_id")
    val menuId: Int,
    @SerializedName("menu_sub_variants")
    val menuSubVariants: List<MenuSubVariant>,
    @SerializedName("name")
    val name: String,
    @SerializedName("selection_type")
    val selectionType: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("variant_type")
    val variantType: Int
)

data class MenuSubVariant(
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