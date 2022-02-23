package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.MenuHeaderBinding
import com.ripenapps.ridechef.model.retrofit.models.Menu
import com.ripenapps.ridechef.model.retrofit.models.MerchantMenuType

class MenuHeaderAdapter(private val context: Context, val listener: (Menu) -> Unit) :
    RecyclerView.Adapter<MenuHeaderAdapter.ViewHolder>() {

    private var menuMerchantTypes = mutableListOf<MerchantMenuType>()
    private lateinit var menuItemAdapter: MenuItemAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.menu_header, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.menuMerchantType = menuMerchantTypes[position]
        setRecyclerView(holder, menuMerchantTypes[position])
    }

    private fun setRecyclerView(holder: ViewHolder, merchantMenuType: MerchantMenuType) {
        menuItemAdapter = MenuItemAdapter(context) { menu -> listener(menu) }
        holder.binding?.menusRecyclerView?.layoutManager = LinearLayoutManager(context)
        holder.binding?.menusRecyclerView?.adapter = menuItemAdapter
        menuItemAdapter.updateList(merchantMenuType.menus)
    }

    override fun getItemCount(): Int {
        return menuMerchantTypes.size
    }

    fun updateList(list: List<MerchantMenuType>?) {
        menuMerchantTypes.clear()
        menuMerchantTypes.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: MenuHeaderBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}