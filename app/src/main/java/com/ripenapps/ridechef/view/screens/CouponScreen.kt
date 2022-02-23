package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentCouponScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.Coupons
import com.ripenapps.ridechef.view.adapters.CouponAdapter

class CouponScreen : Fragment() {

    lateinit var binding: FragmentCouponScreenBinding
    lateinit var couponAdapter: CouponAdapter

    private val args: CouponScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coupon_screen, container, false)
        setAppBar()
        setRecyclerView()
        return binding.root
    }

    private fun setAppBar() {
        binding.appBarId.titleId.text = "Coupons"
        binding.appBarId.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setRecyclerView() {
        couponAdapter = CouponAdapter(requireContext()) { applyCoupon ->
            this.findNavController().previousBackStackEntry?.savedStateHandle?.set("applyCoupon", Gson().toJson(applyCoupon))
            this.findNavController().popBackStack()
        }
        binding.recyclerView.adapter = couponAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val objectList = Gson().fromJson(args.couponsList, Array<Coupons>::class.java).asList()
        Log.e("TAG", "onCreateView: $objectList")
        couponAdapter.updateList(objectList)
    }

}