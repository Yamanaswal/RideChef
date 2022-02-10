package com.ripenapps.ridechef.model.retrofit.retrofit_service

import com.ripenapps.ridechef.model.retrofit.models.LoginRequest
import com.ripenapps.ridechef.model.retrofit.models.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Call
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @POST("api/user/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>



//    @Multipart
//    @POST("api/driver/signup")
//    fun signUp(
//        @PartMap partMap: Map<String?, RequestBody?>?,
//        @Part profile_image: MultipartBody.Part?,
//    ): Call<EmptyResponse>?


}

