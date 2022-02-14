package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.RestaurantCuisineItemBinding
import com.ripenapps.ridechef.databinding.RestaurantCuisineSearchItemBinding
import com.ripenapps.ridechef.model.retrofit.models.Cuisines

//TODO
//Home Adapter
class CuisineRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<CuisineRecyclerViewAdapter.ViewHolder>() {

    var cuisines: List<Cuisines> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_cuisine_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.cuisine = cuisines[position]
    }

    override fun getItemCount(): Int {
        return cuisines.size
    }

    fun updateList(list: List<Cuisines>?) {
        cuisines = list ?: emptyList()
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: RestaurantCuisineItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

    init {
//        this.faqListResponseList = faqListResponseList
    }
}


//TODO
//Search Adapter
class CuisineSearchRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<CuisineSearchRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_cuisine_search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: RestaurantCuisineSearchItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

    init {
//        this.faqListResponseList = faqListResponseList
    }
}





