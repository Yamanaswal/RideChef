package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.RestaurantItemBinding
import com.ripenapps.ridechef.databinding.RestaurantItemSearchBinding
import com.ripenapps.ridechef.model.retrofit.models.Restaurant
import com.ripenapps.ridechef.model.retrofit.models.ViewAllResponseDataData

class FeatureRestaurantRecyclerViewAdapter(
    private val context: Context,
    private val listener: (restaurantItem: Restaurant) -> Unit
) :
    RecyclerView.Adapter<FeatureRestaurantRecyclerViewAdapter.ViewHolder>() {

    private var restaurants: List<Restaurant> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.restaurantData = restaurants[position]

        holder.binding?.restaurantCard?.setOnClickListener {
            listener(restaurants[position])
        }
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    fun updateList(list: List<Restaurant>?) {
        restaurants = list ?: emptyList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: RestaurantItemBinding? = DataBindingUtil.bind(view!!)
    }

}

class FeatureRestaurantSearchRecyclerViewAdapter(
    private val context: Context,
    private val listener: (restaurantItem: ViewAllResponseDataData) -> Unit
) :
    RecyclerView.Adapter<FeatureRestaurantSearchRecyclerViewAdapter.ViewHolder>() {

    private var restaurants = mutableListOf<ViewAllResponseDataData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.restaurantData = restaurants[position]
        holder.binding?.restaurantCard?.setOnClickListener {
            listener(restaurants[position])
        }
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    fun updateList(list: List<ViewAllResponseDataData>?) {
        restaurants.clear()
        restaurants.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: RestaurantItemSearchBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}



