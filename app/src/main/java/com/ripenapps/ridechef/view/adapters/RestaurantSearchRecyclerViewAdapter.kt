package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.RestaurantItemSearchHomeBinding
import com.ripenapps.ridechef.model.retrofit.models.RestaurantListResponseDataData


class RestaurantSearchRecyclerViewAdapter(private val context: Context,val listener: (RestaurantListResponseDataData) -> Unit ) :
    RecyclerView.Adapter<RestaurantSearchRecyclerViewAdapter.ViewHolder>() {

    private val searchRestList = mutableListOf<RestaurantListResponseDataData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item_search_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.restaurantData = searchRestList[position]

        holder.binding?.restaurantCard?.setOnClickListener {
            listener(searchRestList[position])
        }
    }

    override fun getItemCount(): Int {
        return searchRestList.size
    }

    fun updateList(list: List<RestaurantListResponseDataData>?) {
        searchRestList.clear()
        searchRestList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: RestaurantItemSearchHomeBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}


