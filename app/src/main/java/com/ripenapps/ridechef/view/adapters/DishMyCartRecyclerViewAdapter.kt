package com.ripenapps.ridechef.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.DishMyCartBinding
import com.ripenapps.ridechef.model.retrofit.models.Item


class DishMyCartRecyclerViewAdapter(val listener: (Item, String) -> Unit) :
    RecyclerView.Adapter<DishMyCartRecyclerViewAdapter.ViewHolder>() {

    private val searchDishList = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.dish_my_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.dishItem = searchDishList[position]

        holder.binding?.plus?.setOnClickListener {
            listener(searchDishList[position], "plus")
            searchDishList[position].quantity++
            notifyItemChanged(position)
        }

        holder.binding?.minus?.setOnClickListener {
            if (searchDishList[position].quantity > 1) {
                listener(searchDishList[position], "minus")
                searchDishList[position].quantity--
                notifyItemChanged(position)
            }else{
                listener(searchDishList[position], "one")
                searchDishList[position].quantity--
                searchDishList.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return searchDishList.size
    }

    fun updateList(list: List<Item>?) {
        searchDishList.clear()
        searchDishList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: DishMyCartBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}


