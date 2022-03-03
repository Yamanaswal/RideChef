package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.*
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel : ViewModel() {

    private val mainRepo = MainRepo()

    val restaurantDetailsResponse: LiveData<ApiResponse<RestaurantDetailsResponse>>
        get() = mainRepo.restaurantDetailsLiveData

    fun callApiRestaurantDetails(
        token: String? = null,
        restaurantDetailsRequest: RestaurantDetailsRequest
    ) {
        viewModelScope.launch {
            mainRepo.restaurantDetails(token, restaurantDetailsRequest)
        }
    }


    val dishInsideRestResponse: LiveData<ApiResponse<SearchDishHomeResponse>>
        get() = mainRepo.searchDishInsideRestResponseLiveData

    fun callApiSearchDishInsideRest(searchDishInsideRestRequest: SearchDishInsideRestRequest) {
        viewModelScope.launch {
            mainRepo.searchDishInsideRest(searchDishInsideRestRequest)
        }
    }

    val makeFavoriteRestaurantResponse: LiveData<ApiResponse<EmptyResponse>>
        get() = mainRepo.makeFavoriteRestaurantResponseLiveData

    fun callApiMyFavourite(
        token: String,
        makeFavoriteRequest: MakeFavoriteRequest
    ) {
        viewModelScope.launch {
            mainRepo.makeFavoriteRestaurant(token, makeFavoriteRequest)
        }
    }

}