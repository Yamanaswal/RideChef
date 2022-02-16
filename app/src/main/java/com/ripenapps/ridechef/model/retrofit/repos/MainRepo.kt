package com.ripenapps.ridechef.model.retrofit.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.RetrofitServiceGenerator
import com.ripenapps.ridechef.model.retrofit.retrofit_service.ApiService
import androidx.lifecycle.LiveData
import com.android.volley.NetworkError
import com.ripenapps.ridechef.model.retrofit.models.*
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.coroutineScope

class MainRepo {

    /*** Class Name - TAG ***/
    private val tag = "MainRepo"

    /*******************************************************************************
     * Api Service - Create Service
     * ****************************************************************************/
    private var apiService: ApiService =
        RetrofitServiceGenerator.createService(serviceClass = ApiService::class.java)


    /********************************************************************************
     * Mutable And LiveData ApiResponse - Defined Here
     * ******************************************************************************/
    private val loginMutableLiveData = MutableLiveData<ApiResponse<LoginResponse>>()

    val loginLiveData: LiveData<ApiResponse<LoginResponse>>
        get() = loginMutableLiveData

    private val homeMutableLiveData = MutableLiveData<ApiResponse<HomeResponse>>()

    val homeLiveData: LiveData<ApiResponse<HomeResponse>>
        get() = homeMutableLiveData

    private val viewAllMutableLiveData = MutableLiveData<ApiResponse<ViewAllResponse>>()

    val viewAllLiveData: LiveData<ApiResponse<ViewAllResponse>>
        get() = viewAllMutableLiveData

    private val searchDishHomeMutableLiveData =
        MutableLiveData<ApiResponse<SearchDishHomeResponse>>()

    val searchDishHomeLiveData: LiveData<ApiResponse<SearchDishHomeResponse>>
        get() = searchDishHomeMutableLiveData

    private val restaurantListMutableLiveData =
        MutableLiveData<ApiResponse<RestaurantListResponse>>()

    val restaurantListLiveData: LiveData<ApiResponse<RestaurantListResponse>>
        get() = restaurantListMutableLiveData

    private val dishListResponseMutableLiveData =
        MutableLiveData<ApiResponse<DishListResponse>>()

    val dishListResponseLiveData: LiveData<ApiResponse<DishListResponse>>
        get() = dishListResponseMutableLiveData

    private val restaurantDetailsMutableLiveData =
        MutableLiveData<ApiResponse<RestaurantDetailsResponse>>()

    val restaurantDetailsLiveData: LiveData<ApiResponse<RestaurantDetailsResponse>>
        get() = restaurantDetailsMutableLiveData

    /**********************************************************************************
     * Api Methods - Defined Here
     * *******************************************************************************/

    suspend fun loginApi(loginRequest: LoginRequest) {
        try {
            val response = apiService.login(loginRequest)

            loginMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> loginApi: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> loginApi: ${e.message}")
        }
    }


    suspend fun homeApi(token: String?, homeRequest: HomeRequest) {
        try {
            val hashMap = HashMap<String, String>()
            hashMap["latitude"] = homeRequest.latitude.toString()
            hashMap["longitude"] = homeRequest.longitude.toString()

            val response = apiService.home(token, hashMap)

            homeMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> homeApi: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> homeApi: ${e.message}")
        }
    }


    suspend fun viewAllApi(token: String?, viewAllRequest: ViewAllRequest) {
        try {
            val response = apiService.viewAll(token, viewAllRequest)

            viewAllMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> homeApi: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> homeApi: ${e.message}")
        }
    }


    suspend fun searchDishHomeApi(token: String?, searchHomeRequest: SearchHomeRequest) {
        try {
            val response = apiService.searchDishFromHome(token, searchHomeRequest)

            searchDishHomeMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> homeApi: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> homeApi: ${e.message}")
        }
    }

    suspend fun restaurantList(token: String?, restaurantListRequest: RestaurantListRequest) {
        try {
            val response = apiService.restaurantList(token, restaurantListRequest)

            restaurantListMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> homeApi: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> homeApi: ${e.message}")
        }
    }


    suspend fun dishList(token: String?, dishListRequest: DishListRequest) {
        try {
            val response = apiService.dishList(token, dishListRequest)

            dishListResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> homeApi: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> homeApi: ${e.message}")
        }
    }


    suspend fun restaurantDetails(
        token: String?,
        restaurantDetailsRequest: RestaurantDetailsRequest
    ) {
        try {
            val response = apiService.restaurantDetails(token, restaurantDetailsRequest)

            restaurantDetailsMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> homeApi: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> homeApi: ${e.message}")
        }
    }



}