package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.TopBannerItemBinding
import com.ripenapps.ridechef.model.retrofit.models.AdminBanner

class TopBannerRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<TopBannerRecyclerViewAdapter.ViewHolder>() {

    var adminBanners: List<AdminBanner> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.top_banner_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.adminBannerItem = adminBanners[position]
    }

    override fun getItemCount(): Int {
        return adminBanners.size
    }

    fun updateList(list: List<AdminBanner>?) {
        adminBanners = list ?: emptyList()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: TopBannerItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

    init {
//        this.faqListResponseList = faqListResponseList
    }
}





