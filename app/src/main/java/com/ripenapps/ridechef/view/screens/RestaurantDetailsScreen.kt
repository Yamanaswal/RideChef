package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentRestaurantDetailsScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.LoginResponseData
import com.ripenapps.ridechef.model.retrofit.models.MakeFavoriteRequest
import com.ripenapps.ridechef.model.retrofit.models.RestaurantDetailsRequest
import com.ripenapps.ridechef.model.retrofit.models.RestaurantDetailsResponse
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.ApiResponse
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

        viewModel = ViewModelProvider(this)[RestaurantDetailsViewModel::class.java]

        val userData = getUserData(requireContext())

        viewModel.callApiRestaurantDetails(
            restaurantDetailsRequest = RestaurantDetailsRequest(
                menuType = "",
                restaurantId = args.restaurantId,
                search = "",
                vegType = vegType,
                userId = userData?.id.toString()
            )
        )

        setClicks()
        vegAndNonVegListeners(userData)
        setRecyclerViews(userData)
        setObservers(userData)
        return binding.root
    }

    private fun setClicks() {
        binding.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.goToCartButton.setOnClickListener {
            this.findNavController()
                .navigate(RestaurantDetailsScreenDirections.actionRestaurantDetailsScreenToMyCartScreen())
        }

    }

    private fun vegAndNonVegListeners(userData: LoginResponseData?) {
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
                    vegType = vegType,
                    userId = userData?.id.toString()
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
                    vegType = vegType,
                    userId = userData?.id.toString()
                )
            )
        }

    }

    private fun setRecyclerViews(loginResponseData: LoginResponseData?) {

        Log.e("TAG", "onCreateView: ${loginResponseData?.accessToken}")

        menuHeaderAdapter = MenuHeaderAdapter(requireContext()) { menu ->
            //Open Add To Cart
            if (loginResponseData?.accessToken?.isNotEmpty() == true) {
                val addToCartBottomSheet = AddToCartBottomSheet(menu) { menuItem ->
                    viewModel.callApiRestaurantDetails(
                        restaurantDetailsRequest = RestaurantDetailsRequest(
                            menuType = "",
                            restaurantId = args.restaurantId,
                            search = "",
                            vegType = vegType,
                            userId = loginResponseData.id.toString()
                        )
                    )
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
        binding.couponRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.couponRecyclerView.adapter = couponRecyclerViewAdapter

    }

    private fun setObservers(userData: LoginResponseData?) {
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

            setClicksEvents(res, userData)

            if (userData?.accessToken?.isNotEmpty() == true) {
                if (res.response?.data?.totalCartItem?.toInt() == 0) {
                    binding.goToCartButton.visibility = View.GONE
                } else {
                    binding.goToCartButton.visibility = View.VISIBLE
                    binding.goToCartButton.text =
                        "${res.response?.data?.totalCartItem.toString()} Items - $${res.response?.data?.totalCartAmount.toString()}"
                }
            }
        }
    }


    private fun setClicksEvents(
        res: ApiResponse<RestaurantDetailsResponse>,
        userData: LoginResponseData?
    ) {

        //Set Menu Click with Data
        binding.menuButton.setOnClickListener {
            val bottomSheetMenuSearch = MenuTypeBottomSheet(res.response?.data?.merchantMenuTypes)
            bottomSheetMenuSearch.show(parentFragmentManager, "bottomSheetMenuSearch")
        }

        //Set Search Click with Data
        binding.searchMenu.setOnClickListener {
            val bottomSheetDishSearch = SearchDishBottomSheet(res.response?.data?.id)
            bottomSheetDishSearch.show(parentFragmentManager, "bottomSheetDishSearch")
        }

        //Set Click on Favorite Icon
        binding.favIcon.setOnClickListener {

            if (userData?.accessToken != null) {

                if (res.response?.data?.favorite == 0) {
                    res.response?.data?.favorite = 1
                    binding.favIcon.setImageResource(R.drawable.heart_black_border)

                    viewModel.callApiMyFavourite(
                        userData.tokenType + " " + userData.accessToken,
                        MakeFavoriteRequest(res.response?.data?.id.toString())
                    )
                } else {
                    binding.favIcon.setImageResource(R.drawable.heart_white_border)
                    res.response?.data?.favorite = 0


                    viewModel.callApiMyFavourite(
                        userData.tokenType + " " + userData.accessToken,
                        MakeFavoriteRequest(res.response?.data?.id.toString())
                    )
                }

            } else {
                this.findNavController()
                    .navigate(RestaurantDetailsScreenDirections.actionRestaurantDetailsScreenToLoginScreen())
            }
        }

    }


}