package com.ripenapps.ridechef.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.model.retrofit.models.MerchantCusine
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
        textView.text = "$ ${price}"
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