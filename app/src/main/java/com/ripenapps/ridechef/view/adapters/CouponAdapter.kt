package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.CouponItemBinding
import com.ripenapps.ridechef.databinding.CouponOffItemBinding
import com.ripenapps.ridechef.model.retrofit.models.Coupons


class CouponAdapter(private val context: Context,val listener: (Coupons) -> Unit) : RecyclerView.Adapter<CouponAdapter.ViewHolder>() {

    private val couponList = mutableListOf<Coupons>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.coupon_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.coupon = couponList[position]

        holder.binding?.applyButton?.setOnClickListener {
            listener(couponList[position])
        }
    }

    override fun getItemCount(): Int {
        return couponList.size
    }

    fun updateList(list: List<Coupons>?) {
        couponList.clear()
        couponList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: CouponItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}


