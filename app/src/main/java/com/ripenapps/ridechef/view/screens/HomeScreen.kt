package com.ripenapps.ridechef.view.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import coil.load
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentHomeScreenBinding
import com.ripenapps.ridechef.model.HomeScreenType
import com.ripenapps.ridechef.model.retrofit.models.CartInfo
import com.ripenapps.ridechef.model.retrofit.models.HomeRequest
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view.adapters.*
import com.ripenapps.ridechef.view_model.HomeViewModel
import com.yaman.progress_dialog.ProgressAnimatedDialog

class HomeScreen : Fragment() {

    private val TAG = "HomeScreen"
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
//        if (!this::binding.isInitialized) {
            // Inflate the layout for this fragment
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false)
            viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

            //Call Api For Home
            val userdata = getUserData(requireContext())

            viewModel.callApiHome(
                homeRequest = HomeRequest(
                    "23.77",
                    "22.44",
                    userdata?.id.toString()
                )
            )

            setRecyclerViews()
            setClicks()
            setObservers()

            requireActivity().onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
//                    requireActivity().onBackPressed()
                    Log.e(TAG, "handleOnBackPressed: ", )
                }
            })
//        }

        return binding.root
    }

    private fun setViewCart(cartInfo: CartInfo?) {
        if (cartInfo != null) {
            if (cartInfo.restaurantName != null) {
                binding.viewCart.visibility = View.VISIBLE
                binding.orderImage.load(cartInfo.image)
                binding.restaurantName.text = cartInfo.restaurantName
            } else {
                binding.viewCart.visibility = View.GONE
            }
        } else {
            binding.viewCart.visibility = View.GONE
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: ")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG, "onAttach: ")
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
            setViewCart(res.response?.data?.cartInfo)
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

        binding.viewButton.setOnClickListener {
            this.findNavController().navigate(HomeScreenDirections.actionHomeScreenToMyCartScreen())
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
            TrendingRestaurantRecyclerViewAdapter(requireContext()) { restaurantItem ->
                this.findNavController().navigate(
                    HomeScreenDirections.actionHomeScreenToRestaurantDetailsScreen()
                        .setRestaurantId(restaurantItem.id)
                )
            }
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
            FeatureRestaurantRecyclerViewAdapter(requireContext()) { restaurantItem ->
                this.findNavController().navigate(
                    HomeScreenDirections.actionHomeScreenToRestaurantDetailsScreen()
                        .setRestaurantId(restaurantItem.id)
                )
            }
        binding.featuredRestaurantRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.featuredRestaurantRecyclerView)
        binding.featuredRestaurantRecyclerView.adapter = featureRestaurantRecyclerViewAdapter
    }

    private fun setCuisineRecyclerView() {
        cuisineRecyclerViewAdapter = CuisineRecyclerViewAdapter(requireContext()) { cuisineItem ->
            this.findNavController().navigate(
                HomeScreenDirections.actionHomeScreenToSearchRestaurantAndDishScreen()
                    .setCuisineId(cuisineItem.id.toString())
            )
        }
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