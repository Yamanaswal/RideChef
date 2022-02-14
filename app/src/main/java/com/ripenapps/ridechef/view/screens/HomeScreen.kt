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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentHomeScreenBinding
import com.ripenapps.ridechef.model.HomeScreenType
import com.ripenapps.ridechef.model.retrofit.models.HomeRequest
import com.ripenapps.ridechef.view.adapters.*
import com.ripenapps.ridechef.view_model.HomeViewModel
import com.yaman.progress_dialog.ProgressAnimatedDialog

class HomeScreen : Fragment() {

    val TAG = "HomeScreen"
    lateinit var binding: FragmentHomeScreenBinding
    private lateinit var viewModel: HomeViewModel

    private lateinit var topBannerRecyclerViewAdapter: TopBannerRecyclerViewAdapter
    private lateinit var cuisineRecyclerViewAdapter: CuisineRecyclerViewAdapter
    private lateinit var featureRestaurantRecyclerViewAdapter: FeatureRestaurantRecyclerViewAdapter
    private lateinit var trendingRestaurantRecyclerViewAdapter: TrendingRestaurantRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.callApiHome(homeRequest = HomeRequest(23.77, 22.44))

        setRecyclerViews()
        setClicks()
        setObservers()

        return binding.root
    }

    private fun setObservers() {
        val progress = ProgressAnimatedDialog()
        progress.show(childFragmentManager, "progress")

        viewModel.getHomeResponse.observe(this) { res ->
            Log.e(TAG, "setObservers: ${res.response?.status}")
            topBannerRecyclerViewAdapter.updateList(res.response?.data?.adminBanner)
            cuisineRecyclerViewAdapter.updateList(res.response?.data?.cuisines)
            featureRestaurantRecyclerViewAdapter.updateList(res.response?.data?.featuredRestaurant)
            trendingRestaurantRecyclerViewAdapter.updateList(res.response?.data?.trendingRestaurant)
            progress.dismiss()
        }
    }

    private fun setClicks() {
        binding.profileImage.setOnClickListener {
            this.findNavController()
                .navigate(HomeScreenDirections.actionHomeScreenToSideMenuScreen())
        }

        binding.search.setOnClickListener {
            this.findNavController().navigate(
                HomeScreenDirections.actionHomeScreenToHomeSearchScreen()
                    .setScreenType(HomeScreenType.All)
            )
        }

        binding.trendingCuisineViewAll.setOnClickListener {
            this.findNavController().navigate(
                HomeScreenDirections.actionHomeScreenToHomeSearchScreen()
                    .setScreenType(HomeScreenType.Cuisines)
            )
        }

        binding.featuredRestaurantViewAll.setOnClickListener {
            this.findNavController().navigate(
                HomeScreenDirections.actionHomeScreenToHomeSearchScreen()
                    .setScreenType(HomeScreenType.FeaturedRestaurant)
            )
        }

        binding.trendingRestaurantViewAll.setOnClickListener {
            this.findNavController().navigate(
                HomeScreenDirections.actionHomeScreenToHomeSearchScreen()
                    .setScreenType(HomeScreenType.TrendRestaurant)
            )
        }
    }

    private fun setRecyclerViews() {
        //Init Recycler Views
        setTopRecyclerView()
        setCuisineRecyclerView()
        setFeaturedRecyclerView()
        setRestaurantBannerRecyclerView()
        setTrendingRecyclerView()
    }

    private fun setTrendingRecyclerView() {
        trendingRestaurantRecyclerViewAdapter =
            TrendingRestaurantRecyclerViewAdapter(requireContext())
        binding.trendingRestaurantRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.trendingRestaurantRecyclerView)
        binding.trendingRestaurantRecyclerView.adapter = trendingRestaurantRecyclerViewAdapter
    }

    private fun setRestaurantBannerRecyclerView() {
        val restaurantBannerRecyclerViewAdapter =
            RestaurantBannerRecyclerViewAdapter(requireContext())
        binding.restaurantBannerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.restaurantBannerRecyclerView)
        binding.restaurantBannerRecyclerView.adapter = restaurantBannerRecyclerViewAdapter
    }

    private fun setFeaturedRecyclerView() {
        featureRestaurantRecyclerViewAdapter =
            FeatureRestaurantRecyclerViewAdapter(requireContext())
        binding.featuredRestaurantRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.featuredRestaurantRecyclerView)
        binding.featuredRestaurantRecyclerView.adapter = featureRestaurantRecyclerViewAdapter
    }

    private fun setCuisineRecyclerView() {
        cuisineRecyclerViewAdapter = CuisineRecyclerViewAdapter(requireContext())
        binding.trendingCuisineRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.trendingCuisineRecyclerView.adapter = cuisineRecyclerViewAdapter
    }

    private fun setTopRecyclerView() {
        topBannerRecyclerViewAdapter = TopBannerRecyclerViewAdapter(requireContext())
        binding.topBannerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.topBannerRecyclerView)
        binding.topBannerRecyclerView.adapter = topBannerRecyclerViewAdapter
    }


}