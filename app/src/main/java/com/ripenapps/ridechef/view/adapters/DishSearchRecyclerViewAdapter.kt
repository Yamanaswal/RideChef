package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.DishBodyBinding
import com.ripenapps.ridechef.databinding.DishHeaderBinding
import com.ripenapps.ridechef.model.retrofit.models.DishListResponseDataData
import com.ripenapps.ridechef.model.retrofit.models.MerchantMenu

class DishSearchHeaderRecyclerViewAdapter(private val context: Context, val listener: () -> Unit) :
    RecyclerView.Adapter<DishSearchHeaderRecyclerViewAdapter.ViewHolder>() {

    private val searchDishList = mutableListOf<DishListResponseDataData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.dish_header, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.dishHeader = searchDishList[position]
//
        val dishBodyAdapter = DishSearchBodyRecyclerViewAdapter(context) {}
        holder.binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        holder.binding?.recyclerView?.adapter = dishBodyAdapter
        dishBodyAdapter.updateList(searchDishList[position].merchantMenus)
//        holder.binding?.dishCard?.setOnClickListener {
//            listener(searchDishList[position])
//        }
    }

    override fun getItemCount(): Int {
        return searchDishList.size
    }

    fun updateList(list: List<DishListResponseDataData>?) {
        searchDishList.clear()
        searchDishList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: DishHeaderBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}


class DishSearchBodyRecyclerViewAdapter(private val context: Context, val listener: () -> Unit) :
    RecyclerView.Adapter<DishSearchBodyRecyclerViewAdapter.ViewHolder>() {

    private val searchDishList = mutableListOf<MerchantMenu>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.dish_body, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.dishBody = searchDishList[position]
//
//        holder.binding?.dishCard?.setOnClickListener {
//            listener(searchDishList[position])
//        }
    }

    override fun getItemCount(): Int {
        return searchDishList.size
    }

    fun updateList(list: List<MerchantMenu>?) {
        searchDishList.clear()
        searchDishList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: DishBodyBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}

