package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentLoginScreenBinding

class LoginScreen : Fragment() {

    lateinit var binding: FragmentLoginScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_screen, container, false)

        binding.continueButton.setOnClickListener {
            this.findNavController().navigate(LoginScreenDirections.actionLoginScreenToOtpScreen())
        }

        binding.skipButton.setOnClickListener {
            this.findNavController().navigate(LoginScreenDirections.actionLoginScreenToHomeScreen())
        }

        return binding.root
    }

}