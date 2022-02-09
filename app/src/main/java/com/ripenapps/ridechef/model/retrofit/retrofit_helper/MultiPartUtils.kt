package com.ripenapps.ridechef.model.retrofit.retrofit_helper

import android.content.Context
import android.net.Uri
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


/** Get Multipart File  */
fun prepareFilePart(context: Context, partName: String, fileUri: Uri): MultipartBody.Part {

    val file = File(fileUri.path ?: "")

    // create RequestBody instance from file
    val requestFile: RequestBody = RequestBody.create(
        context.contentResolver.getType(fileUri)?.toMediaTypeOrNull(),
        file
    )

    return MultipartBody.Part.createFormData(partName, file.name, requestFile)
}


/** Get Multipart String
 *  converts String to RequestBody
 * */
fun prepareRequestBody(value: String): RequestBody {
    return RequestBody.create("text/plain".toMediaTypeOrNull(), value)
}



/** Get Convert Class to Map from Gson  */
//convert a data class to a map
fun <T> T.serializeToMap(): Map<String, Any> {
    return convert()
}

//convert an object of type I to type O
inline fun <I, reified O> I.convert(): O {
    val json = Gson().toJson(this)
    return Gson().fromJson(json, object : TypeToken<O>() {}.type)
}
/** END **/
