package com.ripenapps.ridechef.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FaqCardBinding
import com.ripenapps.ridechef.model.retrofit.models.FaqResponseDataData

class FaqsAdapter : RecyclerView.Adapter<FaqsAdapter.ViewHolder>() {

    private val faqList = mutableListOf<FaqResponseDataData>()
    private var lastPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.faq_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.faq = faqList[position]

        holder.binding?.questionLayout?.setOnClickListener {
            singleSelection(position)
        }

        //Single Selection View
        if (faqList[position].isOpen) {
            openView(holder)
        } else {
            closeView(holder)
        }

    }

    override fun getItemCount(): Int {
        return faqList.size
    }

    fun updateList(list: List<FaqResponseDataData>?) {
        faqList.clear()
        faqList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }

    private fun singleSelection(position: Int) {
        //Same Position.
        if (lastPos == position) {
            faqList[position].isOpen = !faqList[position].isOpen
            notifyItemChanged(position)
            return
        }
        //Different Position.
        if (lastPos >= 0 && lastPos < faqList.size) {
            faqList[lastPos].isOpen = false
            notifyItemChanged(lastPos)
        }
        faqList[position].isOpen = true
        lastPos = position
        notifyItemChanged(position)
    }


    private fun closeView(holder: ViewHolder) {
        val context = holder.itemView.context
        holder.binding?.questionIcon?.setImageDrawable(context.resources.getDrawable(R.drawable.faq_plus))
        holder.binding?.faqAnswer?.visibility = View.GONE
    }

    private fun openView(holder: ViewHolder) {
        val context = holder.itemView.context
        holder.binding?.questionIcon?.setImageDrawable(context.resources.getDrawable(R.drawable.faq_minus))
        holder.binding?.faqAnswer?.visibility = View.VISIBLE
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: FaqCardBinding? = DataBindingUtil.bind(view!!)
    }


}