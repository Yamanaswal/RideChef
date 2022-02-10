package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.LoginRequest
import com.ripenapps.ridechef.model.retrofit.models.LoginResponse
import com.ripenapps.ridechef.model.retrofit.repos.AuthRepo
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val authRepo = AuthRepo()
    val loginResponse = MutableLiveData<LoginResponse>()

    fun callApiLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            authRepo.loginApi(loginRequest)
            loginResponse.value = authRepo.loginLiveData.value
        }
    }


    override fun onCleared() {
        super.onCleared()
    }

}