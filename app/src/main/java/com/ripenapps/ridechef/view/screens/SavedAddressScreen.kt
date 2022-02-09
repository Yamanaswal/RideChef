package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentSavedAddressScreenBinding
import com.ripenapps.ridechef.view.adapters.MyOrderRecyclerViewAdapter
import com.ripenapps.ridechef.view.adapters.SavedAddressRecyclerViewAdapter


class SavedAddressScreen : Fragment() {

    lateinit var binding: FragmentSavedAddressScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_saved_address_screen,
            container,
            false
        )
        setAppBar()
        setRecyclerView()
        return binding.root
    }

    private fun setRecyclerView() {
        val savedAddressRecyclerAdapter = SavedAddressRecyclerViewAdapter(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = savedAddressRecyclerAdapter
    }


    private fun setAppBar() {
        binding.appBarId.titleId.text = getString(R.string.saved_address)
        binding.appBarId.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}