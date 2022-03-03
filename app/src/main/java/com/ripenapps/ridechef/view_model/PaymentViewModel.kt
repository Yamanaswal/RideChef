package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.PlaceOrderRequest
import com.ripenapps.ridechef.model.retrofit.models.PlaceOrderResponse
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {

    private val mainRepo = MainRepo()

    val placeOrderResponse: LiveData<ApiResponse<PlaceOrderResponse>>
        get() = mainRepo.placeOrderResponseLiveData

    fun callApiPlaceOrder(
        token: String,
        placeOrderRequest: PlaceOrderRequest
    ) {
        viewModelScope.launch {
            mainRepo.placeOrder(token, placeOrderRequest)
        }
    }


}