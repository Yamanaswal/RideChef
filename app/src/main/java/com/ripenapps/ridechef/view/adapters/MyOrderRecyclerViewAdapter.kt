package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.MyOrderItemBinding


class MyOrderRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<MyOrderRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.my_order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 4
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: MyOrderItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
            setRecyclerView()
        }

        private fun setRecyclerView() {
            val myFoodRecyclerViewAdapter = MyFoodRecyclerViewAdapter(context)
            binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
            binding?.recyclerView?.adapter = myFoodRecyclerViewAdapter
        }

    }

}


