package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.MenuVariantItemBinding
import com.ripenapps.ridechef.model.retrofit.models.MenuVariant


class MenuVariantRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<MenuVariantRecyclerViewAdapter.ViewHolder>() {

    private val meuVariantList = mutableListOf<MenuVariant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.menu_variant_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.menuVariant = meuVariantList[position]

        val menuSubVariantRecyclerViewAdapter = MenuSubVariantRecyclerViewAdapter(context)
        holder.binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        holder.binding?.recyclerView?.adapter = menuSubVariantRecyclerViewAdapter
        menuSubVariantRecyclerViewAdapter.updateList(meuVariantList[position].menuSubVariants)
    }

    override fun getItemCount(): Int {
        return meuVariantList.size
    }

    fun updateList(list: List<MenuVariant>?) {
        meuVariantList.clear()
        meuVariantList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: MenuVariantItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}





