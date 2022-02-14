package com.ripenapps.ridechef.model.retrofit.retrofit_service

import com.ripenapps.ridechef.model.retrofit.models.*
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @POST("api/user/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("api/user/home")
    suspend fun home(
        @Header("Authorization") token: String?,
        @QueryMap() homeRequest: Map<String,String>
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

}

