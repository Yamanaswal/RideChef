package com.ripenapps.ridechef.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.view.WindowManager
import android.webkit.MimeTypeMap
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.google.gson.Gson
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.model.retrofit.models.LoginResponseData
import com.ripenapps.ridechef.model.retrofit.models.MerchantCusine
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.text.DecimalFormat

@BindingAdapter(value = ["loadImageUrl"])
fun loadImage(image: ImageView, url: String?) {
    image.load(url) {
        fallback(R.drawable.ride_chef_placeholder)
    }
}

@BindingAdapter(value = ["setCuisineName"])
fun setCuisineName(textView: TextView, merchantCusines: List<MerchantCusine>) {
    if (merchantCusines.isNotEmpty()) {
        val list = mutableListOf<String>()
        for (item: MerchantCusine in merchantCusines) {
            list.add(item.cuisine.name)
        }
        textView.text = list.joinToString(separator = " • ")
    } else {
        textView.text = ""
    }
}

@BindingAdapter(value = ["setRestaurantPrice"])
fun setRestaurantPrice(textView: TextView, price: String) {
    if (price.isNotEmpty()) {
        val dec = DecimalFormat("#.##")
        textView.text = "$ ${dec.format(price.toDouble())} for 2"
    } else {
        textView.text = ""
    }
}

@BindingAdapter(value = ["setMenuPrice"])
fun setMenuPrice(textView: TextView, price: String) {
    if (price.isNotEmpty()) {
        val dec = DecimalFormat("#.##")
        textView.text = "$ $price"
    } else {
        textView.text = ""
    }
}

@BindingAdapter(value = ["setReviews"])
fun setReviews(textView: TextView, review: String) {
    if (review.isNotEmpty()) {
        if (review.length == 1) {
            textView.text = "$review Review"
        } else {
            textView.text = "$review Reviews"
        }
    } else {
        textView.text = ""
    }
}


@BindingAdapter(value = ["setCouponOff"])
fun setCouponOff(textView: TextView, value: Int) {
    textView.text = "Get ${value}% Off"
}

@BindingAdapter(value = ["setCouponText"])
fun setCouponText(textView: TextView, value: String) {
    textView.text = "Valid on orders above $$value"
}

@BindingAdapter(value = ["setAddressType"])
fun setAddressType(textView: TextView, value: Int) {
    when (value) {
        1 -> {
            textView.text = "HOME"
        }
        2 -> {
            textView.text = "WORK"
        }
        else -> {
            textView.text = "OTHER"
        }
    }
}


@BindingAdapter(value = ["setFavIcon"])
fun setFavIcon(image: ImageView, fav: Int) {
    if (fav == 0) {
        image.setImageResource(R.drawable.heart_white_border)
    } else {
        image.setImageResource(R.drawable.heart_black_border)
    }
}

fun getUserData(context: Context): LoginResponseData? {
    return Gson().fromJson(
        PreferencesUtil.getStringPreference(
            context,
            PrefConstants.USERDATA
        ), LoginResponseData::class.java
    )
}

fun makeTransparentStatusBar(activity: Activity, isTransparent: Boolean) {
    if (isTransparent) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    } else {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }
}


fun addSymbolBeforeEditText(editable: Editable?) {
    when {
        editable.isNullOrEmpty() -> return
        Regex("\\$\\d+").matches(editable.toString()) -> return
        editable.toString() == "$" -> editable.clear()
        editable.startsWith("$").not() -> editable.insert(0, "$")
    }
}

fun getMultipartBodyIMG(uploaded_File: String?, keyName: String): MultipartBody.Part? {
    if (!TextUtils.isEmpty(uploaded_File)) {
        if (!(uploaded_File!!.contains("https://"))) {
            val file = File(uploaded_File)
            val image = file.asRequestBody(
                getMimeType(
                    uploaded_File
                )?.toMediaTypeOrNull()
            )
            return MultipartBody.Part.createFormData(keyName, file.name, image)
        }
        return null
    } else
        return null
}

fun getMimeType(url: String): String? {
    var type: String? = null
    val extension = MimeTypeMap.getFileExtensionFromUrl(url)

    if (extension != null) {
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }

    return type
}

