package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.*
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch

class AddToCartViewModel : ViewModel() {

    val mainRepo = MainRepo()

    val dishDetailsResponse: LiveData<ApiResponse<DishDetailsResponse>>
        get() = mainRepo.dishDetailsResponseLiveData

    fun callApiDishDetails(token: String? = null, dishDetailsRequest: DishDetailsRequest) {
        viewModelScope.launch {
            mainRepo.dishDetails(token = token, dishDetailsRequest = dishDetailsRequest)
        }
    }

    val addToCartResponse: LiveData<ApiResponse<AddToCartResponse>>
        get() = mainRepo.addToCartResponseLiveData

    fun callApiAddToCart(token: String, addToCartRequest: AddToCartRequest) {
        viewModelScope.launch {
            mainRepo.addToCart(token = token, addToCartRequest = addToCartRequest)
        }
    }

}