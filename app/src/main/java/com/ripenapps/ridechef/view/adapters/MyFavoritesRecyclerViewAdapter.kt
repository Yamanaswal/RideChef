package com.ripenapps.ridechef.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.RestaurantItemFavBinding
import com.ripenapps.ridechef.model.retrofit.models.MyFavouriteResponse
import com.ripenapps.ridechef.model.retrofit.models.MyFavouriteResponseData
import com.ripenapps.ridechef.model.retrofit.models.SearchDishHomeResponseDataData

class MyFavoritesRecyclerViewAdapter(val listener: (MyFavouriteResponseData) -> Unit) : RecyclerView.Adapter<MyFavoritesRecyclerViewAdapter.ViewHolder>() {

    private val myFavList = mutableListOf<MyFavouriteResponseData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item_fav, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.restItem = myFavList[position]

        holder.binding?.restaurantCard?.setOnClickListener {
            listener(myFavList[position])
        }
    }

    override fun getItemCount(): Int {
        return myFavList.size
    }

    fun updateList(list: List<MyFavouriteResponseData>?) {
        myFavList.clear()
        myFavList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: RestaurantItemFavBinding? = DataBindingUtil.bind(view!!)
    }
}
