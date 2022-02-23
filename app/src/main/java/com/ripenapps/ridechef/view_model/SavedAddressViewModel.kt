package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.*
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch

class SavedAddressViewModel : ViewModel() {

    val mainRepo = MainRepo()


    val saveUserAddressResponse: LiveData<ApiResponse<EmptyResponse>>
        get() = mainRepo.saveUserAddressLiveData

    fun callApiSaveUserAddress(token: String, saveUserAddressRequest: SaveUserAddressRequest) {
        viewModelScope.launch {
            mainRepo.saveUserAddress(token, saveUserAddressRequest)
        }
    }


    val makeDefaultAddressResponse: LiveData<ApiResponse<EmptyResponse>>
        get() = mainRepo.makeDefaultUserAddressLiveData

    fun callApiMakeDefaultUserAddress(
        token: String,
        makeDefaultAddressRequest: MakeDefaultAddressRequest
    ) {
        viewModelScope.launch {
            mainRepo.makeDefaultUserAddress(token, makeDefaultAddressRequest)
        }
    }


    val userAddressResponse: LiveData<ApiResponse<UserAddressesResponse>>
        get() = mainRepo.userAddressLiveData

    fun callApiUserAddress(token: String) {
        viewModelScope.launch {
            mainRepo.userAddress(token)
        }
    }

}