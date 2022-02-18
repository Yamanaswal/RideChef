package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentAddToCartBottomSheetBinding
import com.ripenapps.ridechef.model.retrofit.models.*
import com.ripenapps.ridechef.view.adapters.MenuVariantRecyclerViewAdapter
import com.ripenapps.ridechef.view_model.AddToCartViewModel

class AddToCartBottomSheet(val menu: Menu) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentAddToCartBottomSheetBinding
    lateinit var menuVariantRecyclerViewAdapter: MenuVariantRecyclerViewAdapter
    lateinit var viewModel: AddToCartViewModel
    var dishDetailsResponseData = mutableListOf<DishDetailsResponseData>()
    var addToCartRequest = AddToCartRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_to_cart_bottom_sheet,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[AddToCartViewModel::class.java]

        viewModel.callApiDishDetails(
            dishDetailsRequest = DishDetailsRequest(menuId = menu.id, merchantId = menu.merchantId)
        )

        setRecyclerView()
//        setAddToCartRequest()
        setClicks()
        setObservers()
        return binding.root
    }

    private fun setAddToCartRequest() {
        addToCartRequest.merchantId = dishDetailsResponseData[0].merchantId.toString()
        addToCartRequest.menuId = dishDetailsResponseData[0].id.toString()
        if (dishDetailsResponseData[0].menuVariants.isEmpty()) {
            addToCartRequest.price = dishDetailsResponseData[0].finalPrice
        }else{
            for (menuSubVariant in dishDetailsResponseData[0].menuVariants[0].menuSubVariants){
//                if(menuSubVariant.isDefault)
            }
        }
    }

    private fun setClicks() {
        binding.addToCartButton.setOnClickListener {
            viewModel.callApiAddToCart("", addToCartRequest)
        }
    }

    private fun setObservers() {
        viewModel.dishDetailsResponse.observe(this) { res ->
            Log.e("TAG", "setObservers: ${res.response}")
            menuVariantRecyclerViewAdapter.updateList(res.response?.data?.get(0)?.menuVariants)
            dishDetailsResponseData.clear()
            dishDetailsResponseData.addAll(res.response?.data!!)
        }

        viewModel.addToCartResponse.observe(this) { res ->
            Log.e("TAG", "setObservers: ${res.response}")
        }
    }

    private fun setRecyclerView() {
        menuVariantRecyclerViewAdapter = MenuVariantRecyclerViewAdapter(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = menuVariantRecyclerViewAdapter
    }

}