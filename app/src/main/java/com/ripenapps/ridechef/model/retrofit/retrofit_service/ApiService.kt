package com.ripenapps.ridechef.model.retrofit.retrofit_service

import com.ripenapps.ridechef.model.retrofit.models.*
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @POST("api/user/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("api/user/home")
    suspend fun home(
        @Header("Authorization") token: String?,
        @QueryMap() homeRequest: Map<String, String>
    ): Response<HomeResponse>

    @POST("api/user/view-all")
    suspend fun viewAll(
        @Header("Authorization") token: String?,
        @Body viewAllRequest: ViewAllRequest
    ): Response<ViewAllResponse>

    @POST("api/user/search-dish-from-home")
    suspend fun searchDishFromHome(
        @Header("Authorization") token: String?,
        @Body searchHomeRequest: SearchHomeRequest
    ): Response<SearchDishHomeResponse>

    @POST("api/user/restaurant-list")
    suspend fun restaurantList(
        @Header("Authorization") token: String?,
        @Body restaurantListRequest: RestaurantListRequest
    ): Response<RestaurantListResponse>

    @POST("api/user/dish-list")
    suspend fun dishList(
        @Header("Authorization") token: String?,
        @Body dishListRequest: DishListRequest
    ): Response<DishListResponse>

    @POST("api/user/restaurant-details")
    suspend fun restaurantDetails(
        @Header("Authorization") token: String?,
        @Body restaurantDetailsRequest: RestaurantDetailsRequest
    ): Response<RestaurantDetailsResponse>

    @POST("api/user/add-to-cart")
    suspend fun addToCart(
        @Header("Authorization") token: String,
        @Body addToCartRequest: AddToCartRequest
    ): Response<AddToCartResponse>

    @GET("api/user/cart-details")
    suspend fun cartDetails(
        @Header("Authorization") token: String,
    ): Response<CartDetailsResponse>

    @POST("api/user/dish-details")
    suspend fun dishDetails(
        @Header("Authorization") token: String?,
        @Body dishDetailsRequest: DishDetailsRequest
    ): Response<DishDetailsResponse>

    @POST("api/user/update-cart-item-quantity")
    suspend fun updateCartItemQuantity(
        @Header("Authorization") token: String,
        @Body updateCartItemQuantityRequest: UpdateCartItemQuantityRequest
    ): Response<UpdateCartItemQuantityResponse>


    @POST("api/user/save-user-address")
    suspend fun saveUserAddress(
        @Header("Authorization") token: String,
        @Body saveUserAddressRequest: SaveUserAddressRequest
    ): Response<EmptyResponse>


    @POST("api/user/make-default-user-address")
    suspend fun makeDefaultUserAddress(
        @Header("Authorization") token: String,
        @Body makeDefaultAddressRequest: MakeDefaultAddressRequest
    ): Response<EmptyResponse>

    @GET("api/user/user-address")
    suspend fun userAddress(
        @Header("Authorization") token: String,
    ): Response<UserAddressesResponse>

    @POST("api/user/search-dish-inside-restro")
    suspend fun searchDishInsideRest(
        @Body searchDishInsideRestRequest: SearchDishInsideRestRequest
    ): Response<SearchDishInsideRestResponse>

    @POST("api/user/update-user-profile")
    suspend fun updateUserProfile(
        @Header("Authorization") token: String,
        @Body file: RequestBody,
    ): Response<EmptyResponse>

    @GET("api/user/get-user-profile")
    suspend fun getUserProfile(
        @Header("Authorization") token: String
    ): Response<GetUserProfileResponse>

    @POST("api/user/order-status-details")
    suspend fun orderStatusDetails(
        @Header("Authorization") token: String,
        @Body orderStatusDetailsRequest: OrderStatusDetailsRequest
    ): Response<OrderStatusDetailsResponse>

    @POST("api/user/place-order")
    suspend fun placeOrder(
        @Header("Authorization") token: String,
        @Body placeOrderRequest: PlaceOrderRequest
    ): Response<PlaceOrderResponse>

    @POST("api/user/rate-driver-merchant")
    suspend fun rateDriverMerchant(
        @Header("Authorization") token: String,
        @Body rateDriverMerchantRequest: RateDriverMerchantRequest
    ): Response<EmptyResponse>

    @POST("api/user/make-favorite-restaurant")
    suspend fun makeFavoriteRestaurant(
        @Header("Authorization") token: String,
        @Body makeFavoriteRequest: MakeFavoriteRequest
    ): Response<EmptyResponse>

    @GET("api/user/get-favorite-restaurants")
    suspend fun getFavoriteRestaurants(
        @Header("Authorization") token: String
    ): Response<MyFavouriteResponse>

    @GET("api/user/my-orders")
    suspend fun myOrders(
        @Header("Authorization") token: String
    ): Response<MyOrderResponse>

    @POST("api/user/get-cms")
    suspend fun getCms(
        @Header("Authorization") token: String,
        @Body cmsRequest: CmsRequest,
    ): Response<CmsResponse>

    @GET("api/user/logout")
    suspend fun logout(
        @Header("Authorization") token: String,
    ): Response<EmptyResponse>

    @POST("api/user/faq")
    suspend fun faq(
        @Body faqRequest: FaqRequest
    ): Response<FaqResponse>

}

