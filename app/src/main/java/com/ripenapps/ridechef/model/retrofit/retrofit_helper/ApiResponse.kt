package com.ripenapps.ridechef.model.retrofit.retrofit_helper

import okhttp3.ResponseBody

data class ApiResponse<T>(
    var response: T?,
    var errorBody: ResponseBody?,
    var error: String?)
{}
