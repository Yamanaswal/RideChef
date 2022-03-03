package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.*
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch

class OrderStatusViewModel : ViewModel() {

    val mainRepo = MainRepo()

    val orderStatusDetailsResponse: LiveData<ApiResponse<OrderStatusDetailsResponse>>
        get() = mainRepo.orderStatusResponseResponseLiveData

    fun callApiOrderStatusDetails(
        token: String,
        orderStatusDetailsRequest: OrderStatusDetailsRequest
    ) {
        viewModelScope.launch {
            mainRepo.orderStatusDetails(
                token,
                orderStatusDetailsRequest = orderStatusDetailsRequest
            )
        }
    }

    val rateDriverMerchantResponse: LiveData<ApiResponse<EmptyResponse>>
        get() = mainRepo.rateDriverMerchantResponseLiveData


    fun callApiRateDriverMerchant(
        token: String,
        rateDriverMerchantRequest: RateDriverMerchantRequest
    ) {
        viewModelScope.launch {
            mainRepo.rateDriverMerchant(
                token,
                rateDriverMerchantRequest = rateDriverMerchantRequest
            )
        }
    }

}