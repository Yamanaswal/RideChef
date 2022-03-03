package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentOrderStatusScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.OrderStatusDetailsRequest
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view_model.OrderStatusViewModel

class OrderStatusScreen : Fragment() {

    lateinit var binding: FragmentOrderStatusScreenBinding
    lateinit var viewModel: OrderStatusViewModel
    val args: OrderStatusScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_order_status_screen,
            container,
            false
        )

        if(args.isOrderPlaced){
            this.findNavController().popBackStack(R.id.myCartScreen,true)
        }
        val userData = getUserData(requireContext())

        viewModel = ViewModelProvider(this)[OrderStatusViewModel::class.java]
        viewModel.callApiOrderStatusDetails(userData?.tokenType + " " + userData?.accessToken,
            OrderStatusDetailsRequest(1)
        )

        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.orderStatusDetailsResponse.observe(this) { res ->
            binding.orderDetail = res.response?.data
        }
    }

}