package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.CartDetailsResponse
import com.ripenapps.ridechef.model.retrofit.models.UpdateCartItemQuantityRequest
import com.ripenapps.ridechef.model.retrofit.models.UpdateCartItemQuantityResponse
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch

class MyCartViewModel : ViewModel() {

    private val mainRepo = MainRepo()

    val getCartDetailsResponse: LiveData<ApiResponse<CartDetailsResponse>>
        get() = mainRepo.cartDetailsResponseLiveData

    fun callApiCartDetails(token: String) {
        viewModelScope.launch {
            mainRepo.cartDetails(token)
        }
    }


    val updateCartItemQuantityResponse: LiveData<ApiResponse<UpdateCartItemQuantityResponse>>
        get() = mainRepo.updateCartItemQuantityLiveData

    fun callApiUpdateCartItemQuantity(
        token: String,
        updateCartItemQuantityRequest: UpdateCartItemQuantityRequest
    ) {
        viewModelScope.launch {
            mainRepo.updateCartItemQuantity(token, updateCartItemQuantityRequest)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}