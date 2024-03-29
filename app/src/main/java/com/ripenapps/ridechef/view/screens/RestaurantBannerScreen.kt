package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentRestaurantBannerScreenBinding

class RestaurantBannerScreen : Fragment() {

    lateinit var binding: FragmentRestaurantBannerScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant_banner_screen, container, false)
        setRecyclerView()
        return binding.root
    }

    private fun setRecyclerView() {

    }


}