package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentOrderPlacedSuccessScreenBinding
import com.ripenapps.ridechef.view_model.SplashViewModel
import android.os.Looper


class OrderPlacedSuccessScreen : Fragment() {

    lateinit var binding: FragmentOrderPlacedSuccessScreenBinding
    private val args: OrderPlacedSuccessScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_order_placed_success_screen,
            container,
            false
        )

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            //Do something after 100ms
            this.findNavController().navigate(
                OrderPlacedSuccessScreenDirections.actionOrderPlacedSuccessScreenToOrderStatusScreen(
                    args.orderId
                ).setIsOrderPlaced(true)
            )
        }, 500)
        return binding.root
    }


}