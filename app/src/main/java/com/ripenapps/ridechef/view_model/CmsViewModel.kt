package com.ripenapps.ridechef.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ripenapps.ridechef.model.retrofit.models.*
import com.ripenapps.ridechef.model.retrofit.repos.MainRepo
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
import kotlinx.coroutines.launch

class CmsViewModel : ViewModel() {

    val mainRepo = MainRepo()

    val cmsResponse: LiveData<ApiResponse<CmsResponse>>
        get() = mainRepo.cmsResponseLiveData

    fun callApiCmsData(
        token: String,
        cmsRequest: CmsRequest
    ) {
        viewModelScope.launch {
            mainRepo.getCms(token, cmsRequest)
        }
    }


    val faqResponse: LiveData<ApiResponse<FaqResponse>>
        get() = mainRepo.faqResponseLiveData

    fun callApiFaq(
        faqRequest: FaqRequest
    ) {
        viewModelScope.launch {
            mainRepo.faq(faqRequest)
        }
    }

}