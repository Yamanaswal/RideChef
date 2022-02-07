package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentHomeSearchScreenBinding
import com.ripenapps.ridechef.view.adapters.CuisineRecyclerViewAdapter

class HomeSearchScreen : Fragment() {

    lateinit var binding: FragmentHomeSearchScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home_search_screen,
            container,
            false
        )

        binding.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }

        setCuisineRecyclerView()

        return binding.root
    }

    private fun setCuisineRecyclerView() {
        val cuisineRecyclerViewAdapter = CuisineRecyclerViewAdapter(requireContext())
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = cuisineRecyclerViewAdapter
    }


}