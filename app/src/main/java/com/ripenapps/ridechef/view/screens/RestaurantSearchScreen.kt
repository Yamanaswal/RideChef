package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentRestaurantSearchScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.RestaurantListRequest
import com.ripenapps.ridechef.view.adapters.RestaurantSearchRecyclerViewAdapter
import com.ripenapps.ridechef.view_model.RestaurantAndDishViewModel

class RestaurantSearchScreen(private val args: SearchRestaurantAndDishScreenArgs) : Fragment() {

    lateinit var binding: FragmentRestaurantSearchScreenBinding
    lateinit var viewModel: RestaurantAndDishViewModel
    private lateinit var restaurantSearchRecyclerViewAdapter: RestaurantSearchRecyclerViewAdapter

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
        viewModel.callRestaurantListApi(
            restaurantListRequest = RestaurantListRequest(
                latitude = "24.33",
                longitude = "25.00",
                search = "",
                cuisine_id = ""
            )
        )

        setRecyclerView()
        setObservers()
        return binding.root
    }

    private fun setRecyclerView() {
        restaurantSearchRecyclerViewAdapter =
            RestaurantSearchRecyclerViewAdapter(requireContext()) { resItem ->
                this.findNavController().navigate(
                    SearchRestaurantAndDishScreenDirections.actionSearchRestaurantAndDishScreenToRestaurantDetailsScreen(
                        resItem.id
                    )
                )
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = restaurantSearchRecyclerViewAdapter
    }

    private fun setObservers() {
        viewModel.restaurantListResponse.observe(this) {
            restaurantSearchRecyclerViewAdapter.updateList(it.response?.data?.data)
        }
    }

}