package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.SearchDishHomeResponse
import com.ripenapps.ridechef.model.retrofit.models.SearchHomeRequest
import com.ripenapps.ridechef.model.retrofit.models.ViewAllRequest
import com.ripenapps.ridechef.model.retrofit.models.ViewAllResponse
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch


class ViewAllViewModel : ViewModel() {

    private val mainRepo = MainRepo()

    val getViewAllResponse: LiveData<ApiResponse<ViewAllResponse>>
        get() = mainRepo.viewAllLiveData

    val getSearchDishHomeResponse : LiveData<ApiResponse<SearchDishHomeResponse>>
        get() = mainRepo.searchDishHomeLiveData

    fun callApiViewAll(token: String? = null, viewAllRequest: ViewAllRequest) {
        viewModelScope.launch {
            mainRepo.viewAllApi(token, viewAllRequest)
        }
    }

    fun callApiSearchDishHome(token: String? = null, searchHomeRequest: SearchHomeRequest) {
        viewModelScope.launch {
            mainRepo.searchDishHomeApi(token, searchHomeRequest)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}