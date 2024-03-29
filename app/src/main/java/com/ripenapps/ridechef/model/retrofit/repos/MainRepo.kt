package com.ripenapps.ridechef.model.retrofit.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.RetrofitServiceGenerator
import com.ripenapps.ridechef.model.retrofit.retrofit_service.ApiService
import androidx.lifecycle.LiveData
import com.ripenapps.ridechef.model.retrofit.models.*
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import okhttp3.MultipartBody

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

    private val addToCartResponseMutableLiveData =
        MutableLiveData<ApiResponse<AddToCartResponse>>()

    val addToCartResponseLiveData: LiveData<ApiResponse<AddToCartResponse>>
        get() = addToCartResponseMutableLiveData

    private val cartDetailsMutableLiveData =
        MutableLiveData<ApiResponse<CartDetailsResponse>>()

    val cartDetailsResponseLiveData: LiveData<ApiResponse<CartDetailsResponse>>
        get() = cartDetailsMutableLiveData

    private val dishDetailsMutableLiveData =
        MutableLiveData<ApiResponse<DishDetailsResponse>>()

    val dishDetailsResponseLiveData: LiveData<ApiResponse<DishDetailsResponse>>
        get() = dishDetailsMutableLiveData

    private val updateCartItemQuantityMutableLiveData =
        MutableLiveData<ApiResponse<UpdateCartItemQuantityResponse>>()

    val updateCartItemQuantityLiveData: LiveData<ApiResponse<UpdateCartItemQuantityResponse>>
        get() = updateCartItemQuantityMutableLiveData

    private val saveUserAddressMutableLiveData =
        MutableLiveData<ApiResponse<EmptyResponse>>()

    val saveUserAddressLiveData: LiveData<ApiResponse<EmptyResponse>>
        get() = saveUserAddressMutableLiveData

    private val makeDefaultUserAddressMutableLiveData =
        MutableLiveData<ApiResponse<EmptyResponse>>()

    val makeDefaultUserAddressLiveData: LiveData<ApiResponse<EmptyResponse>>
        get() = makeDefaultUserAddressMutableLiveData

    private val userAddressMutableLiveData =
        MutableLiveData<ApiResponse<UserAddressesResponse>>()

    val userAddressLiveData: LiveData<ApiResponse<UserAddressesResponse>>
        get() = userAddressMutableLiveData

    private val searchDishInsideRestResponseMutableLiveData =
        MutableLiveData<ApiResponse<SearchDishInsideRestResponse>>()

    val searchDishInsideRestResponseLiveData: LiveData<ApiResponse<SearchDishInsideRestResponse>>
        get() = searchDishInsideRestResponseMutableLiveData

    private val updateUserProfileResponseMutableLiveData =
        MutableLiveData<ApiResponse<EmptyResponse>>()

    val updateUserProfileResponseLiveData: LiveData<ApiResponse<EmptyResponse>>
        get() = updateUserProfileResponseMutableLiveData

    private val getUserProfileResponseMutableLiveData =
        MutableLiveData<ApiResponse<GetUserProfileResponse>>()

    val getUserProfileResponseLiveData: LiveData<ApiResponse<GetUserProfileResponse>>
        get() = getUserProfileResponseMutableLiveData

    private val placeOrderMutableLiveData =
        MutableLiveData<ApiResponse<PlaceOrderResponse>>()

    val placeOrderResponseLiveData: LiveData<ApiResponse<PlaceOrderResponse>>
        get() = placeOrderMutableLiveData

    private val rateDriverMerchantResponseMutableLiveData =
        MutableLiveData<ApiResponse<EmptyResponse>>()

    val rateDriverMerchantResponseLiveData: LiveData<ApiResponse<EmptyResponse>>
        get() = rateDriverMerchantResponseMutableLiveData

    private val orderStatusResponseMutableLiveData =
        MutableLiveData<ApiResponse<OrderStatusDetailsResponse>>()

    val orderStatusResponseResponseLiveData: LiveData<ApiResponse<OrderStatusDetailsResponse>>
        get() = orderStatusResponseMutableLiveData

    private val makeFavoriteRestaurantResponseMutableLiveData =
        MutableLiveData<ApiResponse<EmptyResponse>>()

    val makeFavoriteRestaurantResponseLiveData: LiveData<ApiResponse<EmptyResponse>>
        get() = makeFavoriteRestaurantResponseMutableLiveData

    private val getFavoriteRestaurantsResponseMutableLiveData =
        MutableLiveData<ApiResponse<MyFavouriteResponse>>()

    val getFavoriteRestaurantsResponseLiveData: LiveData<ApiResponse<MyFavouriteResponse>>
        get() = getFavoriteRestaurantsResponseMutableLiveData

    private val myOrdersResponseMutableLiveData =
        MutableLiveData<ApiResponse<MyOrderResponse>>()

    val myOrdersResponseLiveData: LiveData<ApiResponse<MyOrderResponse>>
        get() = myOrdersResponseMutableLiveData

    private val cmsResponseMutableLiveData =
        MutableLiveData<ApiResponse<CmsResponse>>()

    val cmsResponseLiveData: LiveData<ApiResponse<CmsResponse>>
        get() = cmsResponseMutableLiveData


    private val faqResponseMutableLiveData =
        MutableLiveData<ApiResponse<FaqResponse>>()

    val faqResponseLiveData: LiveData<ApiResponse<FaqResponse>>
        get() = faqResponseMutableLiveData

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
            hashMap["latitude"] = homeRequest.latitude
            hashMap["longitude"] = homeRequest.longitude
            hashMap["user_id"] = homeRequest.userId

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
            Log.e(tag, "Exception (localizedMessage) -> viewAllApi: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> viewAllApi: ${e.message}")
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
            Log.e(tag, "Exception (localizedMessage) -> searchDishHomeApi: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> searchDishHomeApi: ${e.message}")
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
            Log.e(tag, "Exception (localizedMessage) -> restaurantList: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> restaurantList: ${e.message}")
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
            Log.e(tag, "Exception (localizedMessage) -> dishList: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> dishList: ${e.message}")
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
            Log.e(tag, "Exception (localizedMessage) -> restaurantDetails: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> restaurantDetails: ${e.message}")
        }
    }


    suspend fun addToCart(
        token: String,
        addToCartRequest: AddToCartRequest
    ) {
        try {
            val response = apiService.addToCart(token, addToCartRequest)

            addToCartResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> addToCart: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> addToCart: ${e.message}")
        }
    }


    suspend fun cartDetails(
        token: String
    ) {
        try {
            val response = apiService.cartDetails(token)

            cartDetailsMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> cartDetails: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> cartDetails: ${e.message}")
        }
    }


    suspend fun dishDetails(
        token: String?,
        dishDetailsRequest: DishDetailsRequest
    ) {
        try {
            val response = apiService.dishDetails(token, dishDetailsRequest)

            dishDetailsMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> dishDetails: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> dishDetails: ${e.message}")
        }
    }

    suspend fun updateCartItemQuantity(
        token: String,
        updateCartItemQuantityRequest: UpdateCartItemQuantityRequest
    ) {
        try {
            val response = apiService.updateCartItemQuantity(token, updateCartItemQuantityRequest)

            updateCartItemQuantityMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(
                tag,
                "Exception (localizedMessage) -> updateCartItemQuantity: ${e.localizedMessage}"
            )
            Log.e(tag, "Exception (message) -> updateCartItemQuantity: ${e.message}")
        }
    }


    suspend fun saveUserAddress(
        token: String,
        saveUserAddressRequest: SaveUserAddressRequest
    ) {
        try {
            val response = apiService.saveUserAddress(token, saveUserAddressRequest)

            saveUserAddressMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> saveUserAddress: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> saveUserAddress: ${e.message}")
        }
    }

    suspend fun makeDefaultUserAddress(
        token: String,
        makeDefaultAddressRequest: MakeDefaultAddressRequest
    ) {
        try {
            val response = apiService.makeDefaultUserAddress(token, makeDefaultAddressRequest)

            makeDefaultUserAddressMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(
                tag,
                "Exception (localizedMessage) -> makeDefaultUserAddress: ${e.localizedMessage}"
            )
            Log.e(tag, "Exception (message) -> makeDefaultUserAddress: ${e.message}")
        }
    }

    suspend fun userAddress(
        token: String,
    ) {
        try {
            val response = apiService.userAddress(token)

            userAddressMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> userAddress: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> userAddress: ${e.message}")
        }
    }

    suspend fun searchDishInsideRest(
        searchDishInsideRestRequest: SearchDishInsideRestRequest
    ) {
        try {
            val response = apiService.searchDishInsideRest(searchDishInsideRestRequest)

            searchDishInsideRestResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(
                tag,
                "Exception (localizedMessage) -> searchDishInsideRest: ${e.localizedMessage}"
            )
            Log.e(tag, "Exception (message) -> searchDishInsideRest: ${e.message}")
        }
    }

    suspend fun updateUserProfile(
        token: String,
        requestBody: MultipartBody,
    ) {
        try {
            Log.e("TAG", "updateUserProfile: $requestBody")
            Log.e("TAG", "updateUserProfile: ${requestBody.size}")
            Log.e("TAG", "updateUserProfile: $token")

            val response = apiService.updateUserProfile(token, requestBody)

            updateUserProfileResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )


        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> updateUserProfile: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> updateUserProfile: ${e.message}")
        }
    }

    suspend fun getUserProfile(
        token: String
    ) {
        try {
            val response = apiService.getUserProfile(token)

            getUserProfileResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> getUserProfile: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> getUserProfile: ${e.message}")
        }
    }

    suspend fun placeOrder(
        token: String,
        placeOrderRequest: PlaceOrderRequest
    ) {
        try {
            val response = apiService.placeOrder(token, placeOrderRequest)

            placeOrderMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> getUserProfile: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> getUserProfile: ${e.message}")
        }
    }


    suspend fun orderStatusDetails(
        token: String,
        orderStatusDetailsRequest: OrderStatusDetailsRequest
    ) {
        try {
            val response = apiService.orderStatusDetails(token, orderStatusDetailsRequest)

            orderStatusResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> getUserProfile: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> getUserProfile: ${e.message}")
        }
    }


    suspend fun rateDriverMerchant(
        token: String,
        rateDriverMerchantRequest: RateDriverMerchantRequest
    ) {
        try {
            val response = apiService.rateDriverMerchant(token, rateDriverMerchantRequest)

            rateDriverMerchantResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> getUserProfile: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> getUserProfile: ${e.message}")
        }
    }


    suspend fun makeFavoriteRestaurant(
        token: String,
        makeFavoriteRequest: MakeFavoriteRequest
    ) {
        try {
            val response = apiService.makeFavoriteRestaurant(token, makeFavoriteRequest)

            makeFavoriteRestaurantResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> getUserProfile: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> getUserProfile: ${e.message}")
        }
    }


    suspend fun getFavoriteRestaurants(
        token: String
    ) {
        try {
            val response = apiService.getFavoriteRestaurants(token)

            getFavoriteRestaurantsResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> getUserProfile: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> getUserProfile: ${e.message}")
        }
    }

    suspend fun myOrders(
        token: String
    ) {
        try {
            val response = apiService.myOrders(token)

            myOrdersResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> getUserProfile: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> getUserProfile: ${e.message}")
        }
    }

    suspend fun getCms(
        token: String,
        cmsRequest: CmsRequest
    ) {
        try {
            val response = apiService.getCms(token, cmsRequest)

            cmsResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> getUserProfile: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> getUserProfile: ${e.message}")
        }
    }

    suspend fun logout(
        token: String,
    ) {
        try {
            val response = apiService.logout(token)

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> getUserProfile: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> getUserProfile: ${e.message}")
        }
    }

    suspend fun faq(
        faqRequest: FaqRequest
    ) {
        try {
            val response = apiService.faq(faqRequest)

            faqResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> getUserProfile: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> getUserProfile: ${e.message}")
        }
    }

}