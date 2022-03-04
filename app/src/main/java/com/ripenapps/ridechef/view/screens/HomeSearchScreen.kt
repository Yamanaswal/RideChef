package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentHomeSearchScreenBinding
import com.ripenapps.ridechef.model.HomeScreenType
import com.ripenapps.ridechef.model.retrofit.models.SearchHomeRequest
import com.ripenapps.ridechef.model.retrofit.models.ViewAllRequest
import com.ripenapps.ridechef.model.retrofit.models.ViewAllResponseDataData
import com.ripenapps.ridechef.view.adapters.*
import com.ripenapps.ridechef.view_model.ViewAllViewModel

class HomeSearchScreen : Fragment() {

    lateinit var binding: FragmentHomeSearchScreenBinding
    lateinit var viewModel: ViewAllViewModel
    private val args: HomeSearchScreenArgs by navArgs()

    private lateinit var cuisineSearchRecyclerViewAdapter: CuisineSearchRecyclerViewAdapter
    private lateinit var trendingRestaurantRecyclerViewAdapter: TrendingRestaurantSearchRecyclerViewAdapter
    private lateinit var featureRestaurantRecyclerViewAdapter: FeatureRestaurantSearchRecyclerViewAdapter
    private lateinit var allFoodsRecyclerAdapter: AllFoodsRecyclerAdapter

    private var listViewAll = mutableListOf<ViewAllResponseDataData>()

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
        viewModel = ViewModelProvider(this)[ViewAllViewModel::class.java]


        Log.e("TAG screenType: ", "onCreateView: ${args.screenType}")

        when (args.screenType) {
            HomeScreenType.Cuisines -> {
                setCuisineRecyclerView()
                binding.searchTitle.text = getString(R.string.trending_cuisines)
                binding.search.hint = getString(R.string.search_cuisines)
                viewModel.callApiViewAll(viewAllRequest = ViewAllRequest(
                    "24.34", "24.2323", "", 1))
                onSearchApi(1)
            }
            HomeScreenType.FeaturedRestaurant -> {
                setFeaturedRestaurants()
                binding.searchTitle.text = getString(R.string.featured_restaurants)
                binding.search.hint = getString(R.string.search_restaurant)
                viewModel.callApiViewAll(viewAllRequest = ViewAllRequest("24.34", "24.2323", "", 2))
                onSearchApi(2)
            }
            HomeScreenType.TrendRestaurant -> {
                setTrendingRestaurants()
                binding.searchTitle.text = getString(R.string.trending_restaurants)
                binding.search.hint = getString(R.string.search_restaurant)
                viewModel.callApiViewAll(viewAllRequest = ViewAllRequest("24.34", "24.2323", "", 3))
                onSearchApi(3)
            }
            else -> {
                setAllFoodsData()
                binding.searchTitle.text = ""
                viewModel.callApiSearchDishHome(
                    searchHomeRequest = SearchHomeRequest(
                        "24.34",
                        "24.2323",
                        ""
                    )
                )
                onSearchApi(0)
            }
        }

        setClicks()
        setObservers()

        return binding.root
    }

    private fun onSearchApi(type: Int) {
        Log.e("TAG", "onSearchApi: $type")

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.e("TAG", "onTextChanged: $s")
                //length is 2 or greater only search then.
                if (s?.length!! >= 2) {

                    when (args.screenType) {
                        HomeScreenType.All -> {
                            viewModel.callApiSearchDishHome(
                                searchHomeRequest = SearchHomeRequest(
                                    "23.99",
                                    "23.88",
                                    binding.search.text.toString()
                                )
                            )
                        }
                        else -> {
                            viewModel.callApiViewAll(
                                viewAllRequest = ViewAllRequest(
                                    "24.34",
                                    "24.2323",
                                    binding.search.text.toString(),
                                    type
                                )
                            )
                        }
                    }

                } else if (s.isEmpty()) {

                    when (args.screenType) {
                        HomeScreenType.All -> {
                            Log.e("TAG", "onTextChanged: ${binding.search.text}")
                            viewModel.callApiSearchDishHome(
                                searchHomeRequest = SearchHomeRequest(
                                    "23.99",
                                    "23.88",
                                    binding.search.text.toString()
                                )
                            )
                        }
                        else -> {
                            viewModel.callApiViewAll(
                                viewAllRequest = ViewAllRequest(
                                    "24.34",
                                    "24.2323",
                                    binding.search.text.toString(),
                                    type
                                )
                            )
                        }
                    }

                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun setObservers() {
        viewModel.getViewAllResponse.observe(this) { res ->
            if (res.response?.data?.currentPage == 1) {
                res.response?.data?.data?.let {
                    //Get list
                    listViewAll.clear()
                    listViewAll.addAll(it)

                    //Set in Adapters
                    if (this::cuisineSearchRecyclerViewAdapter.isInitialized) {
                        cuisineSearchRecyclerViewAdapter.updateList(listViewAll)
                    }
                    if (this::trendingRestaurantRecyclerViewAdapter.isInitialized) {
                        trendingRestaurantRecyclerViewAdapter.updateList(listViewAll)
                    }
                    if (this::featureRestaurantRecyclerViewAdapter.isInitialized) {
                        featureRestaurantRecyclerViewAdapter.updateList(listViewAll)
                    }
                }
            } else {
                res.response?.data?.data?.let { listViewAll.addAll(it) }
            }
        }

        viewModel.getSearchDishHomeResponse.observe(this) { res ->
            allFoodsRecyclerAdapter.updateList(res.response?.data?.data)
        }

    }

    private fun setClicks() {
        binding.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setCuisineRecyclerView() {
        cuisineSearchRecyclerViewAdapter =
            CuisineSearchRecyclerViewAdapter(requireContext()) { cuisineItem ->
                this.findNavController().navigate(
                    HomeSearchScreenDirections.actionHomeSearchScreenToSearchRestaurantAndDishScreen()
                        .setCuisineId(cuisineItem.id.toString())
                        .setSearchText(binding.search.text.toString())
                )
            }
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = cuisineSearchRecyclerViewAdapter
    }

    private fun setFeaturedRestaurants() {
        featureRestaurantRecyclerViewAdapter =
            FeatureRestaurantSearchRecyclerViewAdapter(requireContext()) { restaurantItem ->
                this.findNavController().navigate(
                    HomeSearchScreenDirections.actionHomeSearchScreenToRestaurantDetailsScreen(restaurantItem.id)
                )
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = featureRestaurantRecyclerViewAdapter
    }

    private fun setTrendingRestaurants() {
        trendingRestaurantRecyclerViewAdapter =
            TrendingRestaurantSearchRecyclerViewAdapter(requireContext()) { restaurantItem ->
                this.findNavController().navigate(
                    HomeSearchScreenDirections.actionHomeSearchScreenToRestaurantDetailsScreen(restaurantItem.id)
                )
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = trendingRestaurantRecyclerViewAdapter
    }

    private fun setAllFoodsData() {
        allFoodsRecyclerAdapter = AllFoodsRecyclerAdapter() {
            this.findNavController().navigate(
                HomeSearchScreenDirections.actionHomeSearchScreenToSearchRestaurantAndDishScreen().setSearchText(binding.search.text.toString())
            )
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = allFoodsRecyclerAdapter
    }

}
