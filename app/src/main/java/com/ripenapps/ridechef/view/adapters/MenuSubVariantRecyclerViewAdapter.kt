package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.MenuSubVariantsItemBinding
import com.ripenapps.ridechef.model.retrofit.models.MenuSubVariant

class MenuSubVariantRecyclerViewAdapter(
    private val context: Context,
    val listener: (MenuSubVariant,Int,Boolean) -> Unit
) : RecyclerView.Adapter<MenuSubVariantRecyclerViewAdapter.ViewHolder>() {

    private val menuSubVariantList = mutableListOf<MenuSubVariant>()
    private var selectionType = 0 // 1 - for single selection , 2 - for multiple selection
    private var lastPos = -1
    private var isFirst = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.menu_sub_variants_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.menuSubVariant = menuSubVariantList[position]

        if (isFirst) {
            isFirst = false
            if (menuSubVariantList[holder.adapterPosition].isSelect) {
                lastPos = holder.adapterPosition
            }
        }

        holder.binding?.addToCartCard?.setOnClickListener {
            if (selectionType == 1) {
                singleSelection(position)
            } else {
                multipleSelection(position, holder)
            }
        }

        if (selectionType == 1) {
            if (menuSubVariantList[position].isSelect) {
                openView(holder)
                listener(menuSubVariantList[position],selectionType,false)
            } else {
                closeView(holder)
            }
        }
    }

    private fun closeView(holder: ViewHolder) {
        holder.binding?.checkBox?.setImageDrawable(context.resources.getDrawable(R.drawable.checkbox_white))
    }

    private fun openView(holder: ViewHolder) {
        holder.binding?.checkBox?.setImageDrawable(context.resources.getDrawable(R.drawable.checkbox_red))
    }

    private fun multipleSelection(position: Int, holder: ViewHolder) {
        if (menuSubVariantList[position].isSelect) {
            menuSubVariantList[position].isSelect = false
            closeView(holder)
            listener(menuSubVariantList[position],selectionType,false)
        } else {
            menuSubVariantList[position].isSelect = true
            openView(holder)
            Log.e("TAG", "onBindViewHolder: selectionType 2:  $position")
            listener(menuSubVariantList[position],selectionType,true)
        }
    }

    private fun singleSelection(position: Int) {
        //Same Position.
        if (lastPos == position) {
            menuSubVariantList[position].isSelect = !menuSubVariantList[position].isSelect
            notifyItemChanged(position)
//            return
        }
        //Different Position.
        if (lastPos >= 0 && lastPos < menuSubVariantList.size) {
            menuSubVariantList[lastPos].isSelect = false
            notifyItemChanged(lastPos)
        }
        menuSubVariantList[position].isSelect = true
        lastPos = position
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int {
        return menuSubVariantList.size
    }

    fun updateList(list: List<MenuSubVariant>?, variantType: Int, selectionType: Int) {
        menuSubVariantList.clear()
        menuSubVariantList.addAll(list ?: emptyList())
        this.selectionType = selectionType
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: MenuSubVariantsItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}





