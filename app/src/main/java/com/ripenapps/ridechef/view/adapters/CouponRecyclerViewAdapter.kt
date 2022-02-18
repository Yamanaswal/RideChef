package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.CouponOffItemBinding
import com.ripenapps.ridechef.model.retrofit.models.Coupons
import com.ripenapps.ridechef.model.retrofit.models.SearchDishHomeResponseDataData


class CouponRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<CouponRecyclerViewAdapter.ViewHolder>() {

    private val couponList = mutableListOf<Coupons>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.coupon_off_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.coupon = couponList[position]
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
        var binding: CouponOffItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}


