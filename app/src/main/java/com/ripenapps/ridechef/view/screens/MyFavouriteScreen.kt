package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentMyFavouriteScreenBinding
import com.ripenapps.ridechef.view.adapters.FeatureRestaurantRecyclerViewAdapter
import com.ripenapps.ridechef.view.adapters.MyFavoritesRecyclerViewAdapter


class MyFavouriteScreen : Fragment() {

    lateinit var binding: FragmentMyFavouriteScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_my_favourite_screen,
            container,
            false
        )
        setMyFavouritesRecyclerView()
        return binding.root
    }

    private fun setMyFavouritesRecyclerView() {
        val myFavoritesRecyclerViewAdapter = MyFavoritesRecyclerViewAdapter(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = myFavoritesRecyclerViewAdapter
    }


}