package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentRestaurantDetailsScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.RestaurantDetailsRequest
import com.ripenapps.ridechef.view.adapters.CouponRecyclerViewAdapter
import com.ripenapps.ridechef.view.adapters.MenuHeaderAdapter
import com.ripenapps.ridechef.view_model.RestaurantDetailsViewModel


class RestaurantDetailsScreen : Fragment() {

    lateinit var binding: FragmentRestaurantDetailsScreenBinding
    lateinit var viewModel: RestaurantDetailsViewModel
    private val args: RestaurantDetailsScreenArgs by navArgs()

    lateinit var menuHeaderAdapter: MenuHeaderAdapter
    lateinit var couponRecyclerViewAdapter: CouponRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_restaurant_details_screen,
            container,
            false
        )

        binding.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }

        viewModel = ViewModelProvider(this)[RestaurantDetailsViewModel::class.java]

        viewModel.callApiRestaurantDetails(
            restaurantDetailsRequest = RestaurantDetailsRequest(
                menuType = "",
                restaurantId = args.restaurantId,
                search = "",
                vegType = 1
            )
        )

        setRecyclerViews()
        setObservers()
        return binding.root
    }

    private fun setRecyclerViews() {

        menuHeaderAdapter = MenuHeaderAdapter(requireContext()) { menu -> val addToCartBottomSheet = AddToCartBottomSheet(menu)
            addToCartBottomSheet.show(parentFragmentManager,"addToCartBottomSheet")
        }

        binding.recyclerViewMenuHeader.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMenuHeader.adapter = menuHeaderAdapter

        couponRecyclerViewAdapter = CouponRecyclerViewAdapter(requireContext())
        binding.couponRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.couponRecyclerView.adapter = couponRecyclerViewAdapter
    }

    private fun setObservers() {
        viewModel.restaurantDetailsResponse.observe(this) { res ->
            //Set Restaurant Details
            binding.restaurantDetailsData = res.response?.data
            menuHeaderAdapter.updateList(res.response?.data?.merchantMenuTypes)
            couponRecyclerViewAdapter.updateList(res.response?.data?.coupons)
        }
    }

}