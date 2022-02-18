package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentDishSearchScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.DishListRequest
import com.ripenapps.ridechef.view.adapters.DishSearchBodyRecyclerViewAdapter
import com.ripenapps.ridechef.view.adapters.DishSearchHeaderRecyclerViewAdapter
import com.ripenapps.ridechef.view_model.RestaurantAndDishViewModel

class DishSearchScreen(private val args: SearchRestaurantAndDishScreenArgs) : Fragment() {

    lateinit var binding: FragmentDishSearchScreenBinding
    lateinit var viewModel: RestaurantAndDishViewModel
    private lateinit var dishHeaderAdapter: DishSearchHeaderRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dish_search_screen,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[RestaurantAndDishViewModel::class.java]

        viewModel.callDishListApi(
            dishListRequest = DishListRequest(
                "24.77",
                "25.00",
                search = ""
            )
        )

        setRecyclerView()
        setObserver()
        return binding.root
    }

    private fun setRecyclerView() {
        dishHeaderAdapter = DishSearchHeaderRecyclerViewAdapter(requireContext()) {}
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = dishHeaderAdapter

    }

    private fun setObserver() {
        viewModel.dishListResponse.observe(this) {
            dishHeaderAdapter.updateList(it.response?.data?.data)
        }
    }

}