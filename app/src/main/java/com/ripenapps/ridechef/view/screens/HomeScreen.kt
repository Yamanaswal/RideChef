package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentHomeScreenBinding
import com.ripenapps.ridechef.model.HomeScreenType
import com.ripenapps.ridechef.view.adapters.*

class HomeScreen : Fragment() {

    lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false)
        setData()
        return binding.root
    }

    private fun setData() {

        //Init Recycler Views
        setTopRecyclerView()
        setCuisineRecyclerView()
        setFeaturedRecyclerView()
        setRestaurantBannerRecyclerView()
        setTrendingRecyclerView()


        binding.profileImage.setOnClickListener {
            this.findNavController().navigate(HomeScreenDirections.actionHomeScreenToSideMenuScreen())
        }

        binding.search.setOnClickListener {
            this.findNavController().navigate(HomeScreenDirections.actionHomeScreenToHomeSearchScreen().setScreenType(HomeScreenType.All))
        }

        binding.trendingCuisineViewAll.setOnClickListener {
            this.findNavController().navigate(HomeScreenDirections.actionHomeScreenToHomeSearchScreen().setScreenType(HomeScreenType.Cuisines))
        }

        binding.featuredRestaurantViewAll.setOnClickListener {
            this.findNavController().navigate(HomeScreenDirections.actionHomeScreenToHomeSearchScreen().setScreenType(HomeScreenType.FeaturedRestaurant))
        }

        binding.trendingRestaurantViewAll.setOnClickListener {
            this.findNavController().navigate(HomeScreenDirections.actionHomeScreenToHomeSearchScreen().setScreenType(HomeScreenType.TrendRestaurant))
        }



    }

    private fun setTrendingRecyclerView() {
        val trendingRestaurantRecyclerViewAdapter =
            TrendingRestaurantRecyclerViewAdapter(requireContext())
        binding.trendingRestaurantRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
        val featureRestaurantRecyclerViewAdapter =
            FeatureRestaurantRecyclerViewAdapter(requireContext())
        binding.featuredRestaurantRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.featuredRestaurantRecyclerView)
        binding.featuredRestaurantRecyclerView.adapter = featureRestaurantRecyclerViewAdapter
    }

    private fun setCuisineRecyclerView() {
        val cuisineRecyclerViewAdapter = CuisineRecyclerViewAdapter(requireContext())
        binding.trendingCuisineRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.trendingCuisineRecyclerView.adapter = cuisineRecyclerViewAdapter
    }

    private fun setTopRecyclerView() {
        val topBannerRecyclerViewAdapter = TopBannerRecyclerViewAdapter(requireContext())
        binding.topBannerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.topBannerRecyclerView)
        binding.topBannerRecyclerView.adapter = topBannerRecyclerViewAdapter
    }

}