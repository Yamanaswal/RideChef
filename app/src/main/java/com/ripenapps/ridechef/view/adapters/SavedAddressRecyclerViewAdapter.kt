package com.ripenapps.ridechef.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.SavedAddressItemBinding
import com.ripenapps.ridechef.model.retrofit.models.SearchDishHomeResponseDataData
import com.ripenapps.ridechef.model.retrofit.models.UserAddressesData
import com.ripenapps.ridechef.model.retrofit.models.UserAddressesResponse
import com.ripenapps.ridechef.view_model.SavedAddressViewModel


class SavedAddressRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<SavedAddressRecyclerViewAdapter.ViewHolder>() {

    var savedAddressList = mutableListOf<UserAddressesData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.saved_address_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.savedAddress = savedAddressList[position]
    }

    override fun getItemCount(): Int {
        return savedAddressList.size
    }

    fun updateList(list: List<UserAddressesData>?) {
        savedAddressList.clear()
        savedAddressList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: SavedAddressItemBinding? = DataBindingUtil.bind(view!!)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

}


