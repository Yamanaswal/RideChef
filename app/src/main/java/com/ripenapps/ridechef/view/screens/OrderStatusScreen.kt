package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentOrderStatusScreenBinding
import com.ripenapps.ridechef.view_model.OrderStatusViewModel

class OrderStatusScreen : Fragment() {

    lateinit var binding: FragmentOrderStatusScreenBinding
    lateinit var viewModel: OrderStatusViewModel

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
        viewModel = ViewModelProvider(this)[OrderStatusViewModel::class.java]
        return binding.root
    }

}