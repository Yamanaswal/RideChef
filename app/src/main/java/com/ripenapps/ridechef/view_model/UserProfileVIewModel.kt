package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.EmptyResponse
import com.ripenapps.ridechef.model.retrofit.models.GetUserProfileResponse
import com.ripenapps.ridechef.model.retrofit.models.UpdateUserProfileRequest
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch

class UserProfileVIewModel : ViewModel() {

    val mainRepo = MainRepo()


    val getUserProfileResponse: LiveData<ApiResponse<GetUserProfileResponse>>
        get() = mainRepo.getUserProfileResponseResponseLiveData

    fun callApiGetUserProfile(token: String) {
        viewModelScope.launch {
            mainRepo.getUserProfile(token)
        }
    }


    val updateUserProfileResponse: LiveData<ApiResponse<EmptyResponse>>
        get() = mainRepo.updateUserProfileResponseLiveData

    fun callApiUpdateUserProfile(
        token: String,
        updateUserProfileRequest: UpdateUserProfileRequest
    ) {
        viewModelScope.launch {
            mainRepo.updateUserProfile(token, updateUserProfileRequest)
        }
    }

}