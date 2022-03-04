package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentOtpScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.LoginRequest
import com.ripenapps.ridechef.model.retrofit.models.LoginResponse
import com.ripenapps.ridechef.utils.PrefConstants
import com.ripenapps.ridechef.utils.PreferencesUtil
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view_model.OtpViewModel

class OtpScreen : Fragment() {

    lateinit var binding: FragmentOtpScreenBinding
    lateinit var viewModel: OtpViewModel
    private val args: OtpScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp_screen, container, false)
        viewModel = ViewModelProvider(this)[OtpViewModel::class.java]

        Log.e("Mobile Number", "onCreateView: ${args.mobileNumber}")
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
                    args.mobileNumber,
                    fcmToken = "xyz",
                    deviceType = "1"
                )
            )
        }

        binding.changeNumber.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setObservers() {
        viewModel.getLoginResponse.observe(this) { res ->
            if (res.response?.status == 200) {
                Log.e("TAG", "setObservers: ${res.response?.message}")

                // Save User Data
                saveLocalData(res.response)

                val isSkip = PreferencesUtil.getStringPreference(requireContext(),PrefConstants.IS_SKIPPED)

                if(isSkip == "true"){
                    this.findNavController().popBackStack(R.id.loginScreen,true)
                }else{
                    //Navigate to Home
                    this.findNavController().navigate(OtpScreenDirections.actionOtpScreenToHomeScreen())
                }

            }
        }
    }

    private fun saveLocalData(response: LoginResponse?) {
        PreferencesUtil.setStringPreference(
            requireContext(),
            PrefConstants.USERDATA,
            Gson().toJson(response?.data)
        )

        PreferencesUtil.setStringPreference(
            requireContext(),
            PrefConstants.IS_USER_LOGIN, "true"
        )
    }

}

