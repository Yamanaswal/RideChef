package com.ripenapps.ridechef.model.retrofit.repos

import androidx.lifecycle.MutableLiveData
import com.ripenapps.ridechef.model.retrofit.models.LoginRequest
import com.ripenapps.ridechef.model.retrofit.models.LoginResponse
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.RetrofitServiceGenerator
import com.ripenapps.ridechef.model.retrofit.retrofit_service.ApiService
import androidx.lifecycle.LiveData

class AuthRepo {

    var apiService: ApiService = RetrofitServiceGenerator.createService(serviceClass = ApiService::class.java)

    private val loginMutableData = MutableLiveData<LoginResponse>()

    val loginLiveData: LiveData<LoginResponse>
        get() = loginMutableData

    suspend fun loginApi(loginRequest: LoginRequest) {
        val response = apiService.login(loginRequest)
        loginMutableData.postValue(response.body())
    }

}