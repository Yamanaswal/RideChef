package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentMyOrdersScreenBinding
import com.ripenapps.ridechef.view.adapters.MyFavoritesRecyclerViewAdapter
import com.ripenapps.ridechef.view.adapters.MyOrderRecyclerViewAdapter

class MyOrdersScreen : Fragment() {

    lateinit var binding: FragmentMyOrdersScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_orders_screen, container, false)
        setAppBar()
        setRecyclerView()
        return binding.root
    }

    private fun setRecyclerView() {
        val myOrderRecyclerViewAdapter = MyOrderRecyclerViewAdapter(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = myOrderRecyclerViewAdapter
    }

    private fun setAppBar() {
        binding.appBarId.titleId.text = getString(R.string.my_order)
        binding.appBarId.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }


}