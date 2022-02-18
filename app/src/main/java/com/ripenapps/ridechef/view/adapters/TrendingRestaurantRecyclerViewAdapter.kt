package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.RestaurantItemBinding
import com.ripenapps.ridechef.databinding.RestaurantItemSearchBinding
import com.ripenapps.ridechef.model.retrofit.models.Restaurant
import com.ripenapps.ridechef.model.retrofit.models.ViewAllResponseDataData

class TrendingRestaurantRecyclerViewAdapter(
    private val context: Context,
    private val listener: (restaurantItem: Restaurant) -> Unit
) : RecyclerView.Adapter<TrendingRestaurantRecyclerViewAdapter.ViewHolder>() {

    private var trendingRestaurants: List<Restaurant> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.restaurantData = trendingRestaurants[position]

        holder.binding?.restaurantCard?.setOnClickListener {
            listener(trendingRestaurants[position])
        }
    }

    override fun getItemCount(): Int {
        return trendingRestaurants.size
    }

    fun updateList(list: List<Restaurant>?) {
        trendingRestaurants = list ?: emptyList()
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: RestaurantItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}


class TrendingRestaurantSearchRecyclerViewAdapter(
    private val context: Context,
    private val listener: (restaurantItem: ViewAllResponseDataData) -> Unit
) : RecyclerView.Adapter<TrendingRestaurantSearchRecyclerViewAdapter.ViewHolder>() {

    private var trendingRestaurants = mutableListOf<ViewAllResponseDataData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.restaurantData = trendingRestaurants[position]

        holder.binding?.restaurantCard?.setOnClickListener {
            if (trendingRestaurants.isNotEmpty()) {
                listener(trendingRestaurants[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return trendingRestaurants.size
    }

    fun updateList(list: List<ViewAllResponseDataData>?) {
        trendingRestaurants.clear()
        trendingRestaurants.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: RestaurantItemSearchBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}
