package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentOtpScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.LoginRequest
import com.ripenapps.ridechef.view_model.LoginViewModel
import com.ripenapps.ridechef.view_model.OtpViewModel

class OtpScreen : Fragment() {

    lateinit var binding: FragmentOtpScreenBinding
    lateinit var viewModel: OtpViewModel

    val args : OtpScreenArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp_screen, container, false)
        viewModel = ViewModelProvider(this)[OtpViewModel::class.java]

        binding.mobileNumber.text = args.mobileNumber

        setObservers()
        setClicks()
        return binding.root
    }

    private fun setClicks() {
        binding.otpButton.setOnClickListener {

            viewModel.callLoginApi(
                loginRequest = LoginRequest(
                    "Vasundhara",
                    23.99,
                    23.00,
                    "7042108402"
                )
            )

        }
    }

    private fun setObservers() {

        viewModel.getLoginResponse.observe(this) { response ->
            if (response.response?.status == 200) {
                Log.e("TAG", "setObservers: ${response.response?.message}")
            }
        }
    }


}

