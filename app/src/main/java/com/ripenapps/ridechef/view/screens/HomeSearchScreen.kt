package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentHomeSearchScreenBinding
import com.ripenapps.ridechef.model.HomeScreenType
import com.ripenapps.ridechef.view.adapters.AllFoodsRecyclerAdapter
import com.ripenapps.ridechef.view.adapters.CuisineSearchRecyclerViewAdapter
import com.ripenapps.ridechef.view.adapters.FeatureRestaurantRecyclerViewAdapter
import com.ripenapps.ridechef.view.adapters.TrendingRestaurantRecyclerViewAdapter

class HomeSearchScreen : Fragment() {

    lateinit var binding: FragmentHomeSearchScreenBinding
    private val args: HomeSearchScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home_search_screen,
            container,
            false
        )


        when (args.screenType) {
            HomeScreenType.FeaturedRestaurant -> {
                setFeaturedRestaurants()
                binding.searchTitle.text = getString(R.string.featured_restaurants)
            }
            HomeScreenType.TrendRestaurant -> {
                setTrendingRestaurants()
                binding.searchTitle.text = getString(R.string.trending_restaurants)
            }
            HomeScreenType.Cuisines -> {
                setCuisineRecyclerView()
                binding.searchTitle.text = getString(R.string.trending_cuisines)
            }
            else -> {
                setAllFoodsData()
                binding.searchTitle.text = ""
            }
        }

        binding.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }


//        setCuisineRecyclerView()

        return binding.root
    }

    private fun setCuisineRecyclerView() {
        val cuisineSearchRecyclerViewAdapter = CuisineSearchRecyclerViewAdapter(requireContext())
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = cuisineSearchRecyclerViewAdapter
    }

    private fun setFeaturedRestaurants() {
        val featureRestaurantRecyclerViewAdapter = FeatureRestaurantRecyclerViewAdapter(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = featureRestaurantRecyclerViewAdapter
    }

    private fun setTrendingRestaurants() {
        val trendingRestaurantRecyclerViewAdapter =
            TrendingRestaurantRecyclerViewAdapter(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = trendingRestaurantRecyclerViewAdapter
    }

    private fun setAllFoodsData() {
        val allFoodsRecyclerAdapter = AllFoodsRecyclerAdapter(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = allFoodsRecyclerAdapter
    }

}
