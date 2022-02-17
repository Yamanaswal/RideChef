package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.*
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch

class RestaurantAndDishViewModel : ViewModel() {

    private val mainRepo = MainRepo()


    val restaurantListResponse: LiveData<ApiResponse<RestaurantListResponse>>
        get() = mainRepo.restaurantListLiveData


    fun callRestaurantListApi(token: String? = null, restaurantListRequest: RestaurantListRequest) {
        viewModelScope.launch {
            mainRepo.restaurantList(token, restaurantListRequest)
        }
    }

    val dishListResponse: LiveData<ApiResponse<DishListResponse>>
        get() = mainRepo.dishListResponseLiveData


    fun callDishListApi(token: String? = null, dishListRequest: DishListRequest) {
        viewModelScope.launch {
            mainRepo.dishList(token, dishListRequest)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}