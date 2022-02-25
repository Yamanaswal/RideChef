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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentMyCartScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.Coupons
import com.ripenapps.ridechef.model.retrofit.models.UpdateCartItemQuantityRequest
import com.ripenapps.ridechef.utils.addSymbolBeforeEditText
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view.adapters.DishMyCartRecyclerViewAdapter
import com.ripenapps.ridechef.view_model.MyCartViewModel

class MyCartScreen : Fragment() {

    lateinit var binding: FragmentMyCartScreenBinding
    lateinit var viewModel: MyCartViewModel
    private lateinit var dishMyCartRecyclerView: DishMyCartRecyclerViewAdapter
    var couponList = mutableListOf<Coupons>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (!this::binding.isInitialized) {
            // Inflate the layout for this fragment
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_my_cart_screen,
                container,
                false
            )
            viewModel = ViewModelProvider(this)[MyCartViewModel::class.java]
            val userdata = getUserData(requireContext())
            viewModel.callApiCartDetails(userdata?.tokenType + " " + userdata?.accessToken)
            setAppBar()
            setRecyclerView()
            setClicks()
            setObservers()

            binding.billDetailsEditText.addTextChangedListener(
                object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {

                    }

                    override fun afterTextChanged(editable: Editable?) {
                        addSymbolBeforeEditText(editable)
                    }
                })

            this.findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("applyCoupon")
                ?.observe(viewLifecycleOwner) { data ->
                    // Do something with the data.
                    Log.e("currentBackStackEntry", "onCreateView: $data")
                }
        }

        return binding.root
    }

    private fun setClicks() {
        binding.appleCouponCard.setOnClickListener {
            this.findNavController().navigate(
                MyCartScreenDirections.actionMyCartScreenToCouponScreen(Gson().toJson(couponList))
            )
        }
    }

    private fun setRecyclerView() {
        val userdata = getUserData(requireContext())
        viewModel.callApiCartDetails(userdata?.tokenType + " " + userdata?.accessToken)

        dishMyCartRecyclerView = DishMyCartRecyclerViewAdapter { item, type ->
            if (type == "plus") {

                if (item.variant != null) {
                    viewModel.callApiUpdateCartItemQuantity(
                        userdata?.tokenType + " " + userdata?.accessToken,
                        UpdateCartItemQuantityRequest(
                            getPlusValue(item.price, item.variant.price),
                            item.quantity,
                            item.id,
                            type = "update"
                        )
                    )
                } else {
                    viewModel.callApiUpdateCartItemQuantity(
                        userdata?.tokenType + " " + userdata?.accessToken,
                        UpdateCartItemQuantityRequest(
                            getPlusValue(
                                item.price,
                                item.menuDetails.price
                            ), item.quantity, item.id, type = "update"
                        )
                    )
                }

            }
            //minus
            else if (type == "minus") {

                if (item.variant != null) {
                    viewModel.callApiUpdateCartItemQuantity(
                        userdata?.tokenType + " " + userdata?.accessToken,
                        UpdateCartItemQuantityRequest(
                            getMinusValue(item.price, item.variant.price),
                            item.quantity,
                            item.id,
                            type = "update"
                        )
                    )
                } else {
                    viewModel.callApiUpdateCartItemQuantity(
                        userdata?.tokenType + " " + userdata?.accessToken,
                        UpdateCartItemQuantityRequest(
                            getMinusValue(
                                item.price,
                                item.menuDetails.price
                            ), item.quantity, item.id, type = "update"
                        )
                    )
                }

            }
            //At Delete Item.
            else {
                viewModel.callApiUpdateCartItemQuantity(
                    userdata?.tokenType + " " + userdata?.accessToken,
                    UpdateCartItemQuantityRequest("", 0, item.id, type = "delete")
                )

                if (dishMyCartRecyclerView.itemCount == 0) {
                    requireActivity().onBackPressed()
                }
            }

        }
        binding.dishRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.dishRecyclerView.adapter = dishMyCartRecyclerView
    }

    private fun setObservers() {
        viewModel.getCartDetailsResponse.observe(this) { res ->
            if (res.response?.status == 200) {
                binding.myCartDetails = res.response?.data
                dishMyCartRecyclerView.updateList(res.response?.data?.items)
                couponList.clear()
                if (!res.response?.data?.coupons.isNullOrEmpty()) {
                    couponList.addAll(res.response?.data?.coupons!!)
                }
            }
        }
    }

    private fun setAppBar() {
        binding.appBarId.titleId.text = getString(R.string.my_cart)
        binding.appBarId.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun getPlusValue(mainPrice: String, price: String): String {
        return (mainPrice.toDouble() + price.toDouble()).toString()
    }

    private fun getMinusValue(mainPrice: String, price: String): String {
        return (mainPrice.toDouble() - price.toDouble()).toString()
    }
}