package com.ripenapps.ridechef.view.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.MenuTypeItemBinding
import com.ripenapps.ridechef.model.retrofit.models.MerchantMenuType


class MenuTypeRecyclerViewAdapter(val listener: (MerchantMenuType) -> Unit) :
    RecyclerView.Adapter<MenuTypeRecyclerViewAdapter.ViewHolder>() {

    private val menuTypeList = mutableListOf<MerchantMenuType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.menu_type_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.menuMerchantType = menuTypeList[position]

        holder.binding?.menuCard?.setOnClickListener {
            listener(menuTypeList[position])
        }
    }

    override fun getItemCount(): Int {
        return menuTypeList.size
    }

    fun updateList(list: List<MerchantMenuType>?) {
        menuTypeList.clear()
        menuTypeList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: MenuTypeItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}


