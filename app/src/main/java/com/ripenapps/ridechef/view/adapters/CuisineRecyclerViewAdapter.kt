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
import com.ripenapps.ridechef.model.retrofit.models.ViewAllResponseDataData

//TODO
//Home Adapter
class CuisineRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<CuisineRecyclerViewAdapter.ViewHolder>() {

    private var cuisines: List<Cuisines> = emptyList()

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

}


//TODO
//Search Adapter
class CuisineSearchRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<CuisineSearchRecyclerViewAdapter.ViewHolder>() {

    private var cuisines = mutableListOf<ViewAllResponseDataData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_cuisine_search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.cuisine = cuisines[position]
    }

    override fun getItemCount(): Int {
        return cuisines.size
    }

    fun updateList(list: List<ViewAllResponseDataData>?) {
        cuisines.clear()
        cuisines.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: RestaurantCuisineSearchItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}





