package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.LoginRequest
import com.ripenapps.ridechef.model.retrofit.models.LoginResponse
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val mainRepo = MainRepo()

    override fun onCleared() {
        super.onCleared()
    }

}