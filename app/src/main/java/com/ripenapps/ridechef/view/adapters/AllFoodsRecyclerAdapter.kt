package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.RestaurantFoodItemBinding
import com.ripenapps.ridechef.model.retrofit.models.SearchDishHomeResponseDataData


class AllFoodsRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<AllFoodsRecyclerAdapter.ViewHolder>() {

    private val searchDishList = mutableListOf<SearchDishHomeResponseDataData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_food_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.dishItem = searchDishList[position]
    }

    override fun getItemCount(): Int {
        return searchDishList.size
    }

    fun updateList(list: List<SearchDishHomeResponseDataData>?) {
        searchDishList.clear()
        searchDishList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: RestaurantFoodItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}


