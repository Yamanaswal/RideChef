package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentAllowLocationScreenBinding

class AllowLocationScreen : Fragment() {

    lateinit var binding: FragmentAllowLocationScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_allow_location_screen,
            container,
            false
        )

        binding.confirmLocation.setOnClickListener {
            this.findNavController().navigate(AllowLocationScreenDirections.actionAllowLocationScreenToLoginScreen())
        }

        binding.changeLocation.setOnClickListener {
//            this.findNavController().navigate(AllowLocationScreenDirections.actionAllowLocationScreenToChangeLocation())
        }

        return binding.root
    }

}