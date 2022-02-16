package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.MenuItemBinding
import com.ripenapps.ridechef.model.retrofit.models.Menu

class MenuItemAdapter(private val context: Context) :
    RecyclerView.Adapter<MenuItemAdapter.ViewHolder>() {

    private var menus = mutableListOf<Menu>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.menu = menus[position]
    }

    override fun getItemCount(): Int {
        return menus.size
    }

    fun updateList(list: List<Menu>?) {
        menus.clear()
        menus.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: MenuItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}