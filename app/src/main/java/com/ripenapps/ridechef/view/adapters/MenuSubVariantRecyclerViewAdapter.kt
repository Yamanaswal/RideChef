package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.MenuSubVariantsItemBinding
import com.ripenapps.ridechef.model.retrofit.models.MenuSubVariant


class MenuSubVariantRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<MenuSubVariantRecyclerViewAdapter.ViewHolder>() {

    private val menuSubVariantList = mutableListOf<MenuSubVariant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_sub_variants_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.menuSubVariant = menuSubVariantList[position]
    }

    override fun getItemCount(): Int {
        return menuSubVariantList.size
    }

    fun updateList(list: List<MenuSubVariant>?) {
        menuSubVariantList.clear()
        menuSubVariantList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: MenuSubVariantsItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}





