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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UserProfileVIewModel : ViewModel() {

    val mainRepo = MainRepo()

    val getUserProfileResponse: LiveData<ApiResponse<GetUserProfileResponse>>
        get() = mainRepo.getUserProfileResponseLiveData

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
        val builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)

        builder.addFormDataPart("name", updateUserProfileRequest.name)
        builder.addFormDataPart("dob", updateUserProfileRequest.dob)
        builder.addFormDataPart("email", updateUserProfileRequest.email)

        if (updateUserProfileRequest.profileImage != null) {
            val fileCoverImage = File(updateUserProfileRequest.profileImage!!)
            builder.addFormDataPart(
                "profile_image",
                fileCoverImage.name + System.currentTimeMillis(),
                RequestBody.create("multipart/form-data".toMediaTypeOrNull(), fileCoverImage)
            )
        }
        val requestBody: MultipartBody = builder.build()

        viewModelScope.launch {
            mainRepo.updateUserProfile(token, requestBody)
        }

    }

}