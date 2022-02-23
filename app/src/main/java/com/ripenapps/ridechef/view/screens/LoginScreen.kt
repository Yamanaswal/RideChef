package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast.LENGTH_SHORT
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ripenapps.ridechef.MainActivity
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentLoginScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.LoginRequest
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.InternetConnection
import com.ripenapps.ridechef.view_model.LoginViewModel
import com.yaman.validations.validateImage
import com.yaman.validations.validateMobileNumber

class LoginScreen : Fragment() {

    lateinit var binding: FragmentLoginScreenBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_screen, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        setClicks()
        setObservers()

        return binding.root
    }

    private fun setObservers() {

    }

    private fun setClicks() {
        binding.continueButton.setOnClickListener {
            continueButtonClick()
        }

        binding.skipButton.setOnClickListener {
            this.findNavController().navigate(LoginScreenDirections.actionLoginScreenToHomeScreen())
        }
    }

    private fun continueButtonClick() {
        val validMobile = validateMobileNumber(binding.mobileNumber.text.toString())
        if(validMobile.validStatus){
            this.findNavController().navigate(LoginScreenDirections.actionLoginScreenToOtpScreen().setMobileNumber(binding.countryCode.selectedCountryCode + "-" + binding.mobileNumber.text.toString()))
        }else{
            Snackbar.make(requireView(),validMobile.validReason,2000).show()
        }

    }

//    fun validateMobileNumber(mobileNumber: String): Valid {
//
//        if (mobileNumber.isEmpty()) {
//            return Valid(
//                validStatus = false,
//                validReason = "Please Enter Mobile Number."
//            )
//        } else if (mobileNumber.length < 9 || mobileNumber.length > 13) {
//            return Valid(
//                validStatus = false,
//                validReason = "Please Enter Mobile Number Between 9 to 12."
//            )
//        }
//
//        return Valid(validStatus = true, validReason = "")
//    }
}