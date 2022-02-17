package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentRestaurantSearchScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.RestaurantListRequest
import com.ripenapps.ridechef.view_model.RestaurantAndDishViewModel

class RestaurantSearchScreen : Fragment() {

    lateinit var binding: FragmentRestaurantSearchScreenBinding
    lateinit var viewModel : RestaurantAndDishViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_restaurant_search_screen,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[RestaurantAndDishViewModel::class.java]
        viewModel.callRestaurantListApi(restaurantListRequest = RestaurantListRequest("24.33","25.00","","1"))
        return binding.root
    }

}