package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.HomeRequest
import com.ripenapps.ridechef.model.retrofit.models.HomeResponse
import com.ripenapps.ridechef.model.retrofit.models.LoginResponse
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import com.yaman.progress_dialog.ProgressAnimatedDialog
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val mainRepo = MainRepo()

    val getHomeResponse: LiveData<ApiResponse<HomeResponse>>
        get() = mainRepo.homeLiveData

    fun callApiHome(token: String? = null,homeRequest: HomeRequest) {
        viewModelScope.launch {
            mainRepo.homeApi(token,homeRequest)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}