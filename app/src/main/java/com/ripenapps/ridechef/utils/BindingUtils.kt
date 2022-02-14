package com.ripenapps.ridechef.utils

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.Coil
import coil.load
import com.ripenapps.ridechef.model.retrofit.models.MerchantCusine
import java.text.DecimalFormat

@BindingAdapter(value = ["loadImageUrl"])
fun loadImage(image: ImageView, url: String?) {
    image.load(url)
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


