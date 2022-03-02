package com.ripenapps.ridechef.model.retrofit.retrofit_helper

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


fun prepareFilePart(context: Context, partName: String, fileUri: Uri): MultipartBody.Part {

    val file = File(fileUri.path ?: "")

    // create RequestBody instance from file
    val requestFile: RequestBody = RequestBody.create(
        context.contentResolver.getType(fileUri)!!.toMediaTypeOrNull(),
        file
    )


    return MultipartBody.Part.createFormData(partName, file.name, requestFile)
}


fun createRequestBody(string: String): RequestBody {
    return string.toRequestBody(
        "multipart/form-data".toMediaTypeOrNull(),
    )
}

/** END **/
