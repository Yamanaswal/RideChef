package com.ripenapps.ridechef.view.screens

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentRestaurantDetailsScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.RestaurantDetailsRequest
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view.adapters.CouponRecyclerViewAdapter
import com.ripenapps.ridechef.view.adapters.MenuHeaderAdapter
import com.ripenapps.ridechef.view_model.RestaurantDetailsViewModel

class RestaurantDetailsScreen : Fragment() {

    lateinit var binding: FragmentRestaurantDetailsScreenBinding
    lateinit var viewModel: RestaurantDetailsViewModel
    private val args: RestaurantDetailsScreenArgs by navArgs()

    lateinit var menuHeaderAdapter: MenuHeaderAdapter
    lateinit var couponRecyclerViewAdapter: CouponRecyclerViewAdapter
    var vegType = ""

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
                vegType = vegType
            )
        )

        vegAndNonVegListeners()

        setRecyclerViews()
        setObservers()
        return binding.root
    }

    private fun vegAndNonVegListeners() {
        binding.vegSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            vegType = if (isChecked) {
                "1"
            } else {
                ""
            }

            viewModel.callApiRestaurantDetails(
                restaurantDetailsRequest = RestaurantDetailsRequest(
                    menuType = "",
                    restaurantId = args.restaurantId,
                    search = "",
                    vegType = vegType
                )
            )
        }

        binding.nonVegSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            vegType = if (isChecked) {
                "2"
            } else {
                ""
            }

            viewModel.callApiRestaurantDetails(
                restaurantDetailsRequest = RestaurantDetailsRequest(
                    menuType = "",
                    restaurantId = args.restaurantId,
                    search = "",
                    vegType = vegType
                )
            )
        }



    }

    private fun setRecyclerViews() {

        val loginResponseData = getUserData(requireContext())
        Log.e("TAG", "onCreateView: ${loginResponseData?.accessToken}")

        menuHeaderAdapter = MenuHeaderAdapter(requireContext()) { menu ->
            //Open Add To Cart
            if (loginResponseData?.accessToken?.isNotEmpty() == true) {
                val addToCartBottomSheet = AddToCartBottomSheet(menu) {
                    it.totalCartItem
                }
                addToCartBottomSheet.show(parentFragmentManager, "addToCartBottomSheet")
            } else {
                this.findNavController()
                    .navigate(RestaurantDetailsScreenDirections.actionRestaurantDetailsScreenToLoginScreen())
            }
        }

        binding.recyclerViewMenuHeader.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMenuHeader.adapter = menuHeaderAdapter

        couponRecyclerViewAdapter = CouponRecyclerViewAdapter(requireContext())
        binding.couponRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.couponRecyclerView.adapter = couponRecyclerViewAdapter

    }

    private fun setObservers() {
        viewModel.restaurantDetailsResponse.observe(this) { res ->
            //Set Restaurant Details
            binding.restaurantDetailsData = res.response?.data
            menuHeaderAdapter.updateList(res.response?.data?.merchantMenuTypes)
            couponRecyclerViewAdapter.updateList(res.response?.data?.coupons)

            if (res.response?.data?.coupons?.size!! > 0) {
                binding.lineTwo.visibility = View.VISIBLE
            } else {
                binding.lineTwo.visibility = View.GONE
            }
        }
    }

}