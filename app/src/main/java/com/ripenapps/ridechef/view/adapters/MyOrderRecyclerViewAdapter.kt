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
import com.ripenapps.ridechef.model.retrofit.models.MyOrderResponseDataData


class MyOrderRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<MyOrderRecyclerViewAdapter.ViewHolder>() {

    private val myOrderList = mutableListOf<MyOrderResponseDataData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.my_order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.myOrderItem = myOrderList[position]
        setRecyclerView(holder.binding)

        holder.binding?.buttonRepeatOrder?.setOnClickListener {

        }
    }

    private fun setRecyclerView(binding: MyOrderItemBinding?) {
        val myFoodRecyclerViewAdapter = MyFoodRecyclerViewAdapter(context)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.adapter = myFoodRecyclerViewAdapter
    }

    override fun getItemCount(): Int {
        return myOrderList.size
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: MyOrderItemBinding? = DataBindingUtil.bind(view!!)
    }

}


