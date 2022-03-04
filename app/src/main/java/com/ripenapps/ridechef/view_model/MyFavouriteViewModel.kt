package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.MyFavouriteResponse
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch

class MyFavouriteViewModel : ViewModel() {

    val mainRepo = MainRepo()

    val getFavoriteRestaurantsResponse: LiveData<ApiResponse<MyFavouriteResponse>>
        get() = mainRepo.getFavoriteRestaurantsResponseLiveData

    fun callApiMyFavourite(token: String) {
        viewModelScope.launch {
            mainRepo.getFavoriteRestaurants(token)
        }
    }
}