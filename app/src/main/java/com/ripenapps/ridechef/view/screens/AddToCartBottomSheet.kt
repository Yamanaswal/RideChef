package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentAddToCartBottomSheetBinding
import com.ripenapps.ridechef.model.retrofit.models.*
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view.adapters.MenuVariantRecyclerViewAdapter
import com.ripenapps.ridechef.view_model.AddToCartViewModel

class AddToCartBottomSheet(val menu: Menu,val listener : (AddToCartResponseData) -> Unit) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentAddToCartBottomSheetBinding
    lateinit var menuVariantRecyclerViewAdapter: MenuVariantRecyclerViewAdapter
    lateinit var viewModel: AddToCartViewModel
    var dishDetailsResponseData = mutableListOf<DishDetailsResponseData>()
    var addToCartRequest = AddToCartRequest()


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
            dishDetailsRequest = DishDetailsRequest(
                menuId = menu.id,
                merchantId = menu.merchantId
            )
        )

        setRecyclerView()
        setClicks()
        setObservers()
        return binding.root
    }

    private fun setAddToCartRequest() {
        var default = 0
        if (dishDetailsResponseData.size > 0) {
            for (i in 0 until dishDetailsResponseData.size) {
                //All Required
                addToCartRequest.merchantId = dishDetailsResponseData[i].merchantId.toString()
                addToCartRequest.menuId = dishDetailsResponseData[i].id.toString()

                if (dishDetailsResponseData[i].menuVariants.isNotEmpty()) {
                    for (j in dishDetailsResponseData[i].menuVariants.indices) {

                        //Size
                        if (dishDetailsResponseData[i].menuVariants[j].variantType == 1) {

                            for (k in dishDetailsResponseData[i].menuVariants[j].menuSubVariants.indices) {
                                if (dishDetailsResponseData[i].menuVariants[j].menuSubVariants[k].isDefault == 1) {

                                    // Default Values
                                    dishDetailsResponseData[i].menuVariants[j].menuSubVariants[k].isSelect =
                                        true
                                    addToCartRequest.menuSubVariantId =
                                        dishDetailsResponseData[i].menuVariants[j].menuSubVariants[k].id.toString()
                                    addToCartRequest.price =
                                        dishDetailsResponseData[i].menuVariants[j].menuSubVariants[k].price
                                }
                            }

                        }
                    }
                } else {
                    //price
                    addToCartRequest.price = dishDetailsResponseData[i].finalPrice
                }

            }
        }
    }

    private fun setClicks() {
        val loginData = getUserData(requireContext())

        binding.addToCartButton.setOnClickListener {
            viewModel.callApiAddToCart(
                loginData?.tokenType + " " + loginData?.accessToken,
                addToCartRequest
            )
        }
    }

    private fun setObservers() {
        viewModel.dishDetailsResponse.observe(this) { res ->
            Log.e("TAG", "setObservers: ${res.response}")
            menuVariantRecyclerViewAdapter.updateList(res.response?.data?.get(0)?.menuVariants)
            dishDetailsResponseData.clear()
            dishDetailsResponseData.addAll(res.response?.data!!)
            setAddToCartRequest()
        }

        viewModel.addToCartResponse.observe(this) { res ->
            Log.e("TAG", "setObservers: addToCartResponse : ${res.response}")
            if (res.response?.status == 200) {
                listener(res.response!!.data)
                dismiss()
            } else {
                Toast.makeText(requireContext(), res.response?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setRecyclerView() {
        menuVariantRecyclerViewAdapter =
            MenuVariantRecyclerViewAdapter(requireContext()) { item, i, isSelect ->
                if (i == 1) {
                    addToCartRequest.menuSubVariantId = item.id.toString()
                } else {
                    if (isSelect) {
                        addToCartRequest.addOns?.add(AddOns(item.id.toString(), item.price))
                    } else {
                        addToCartRequest.addOns?.remove(AddOns(item.id.toString(), item.price))
                    }
                }
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = menuVariantRecyclerViewAdapter
    }

}