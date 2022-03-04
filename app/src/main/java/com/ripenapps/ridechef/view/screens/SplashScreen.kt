package com.ripenapps.ridechef.view.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentSplashScreenBinding
import com.ripenapps.ridechef.utils.PrefConstants
import com.ripenapps.ridechef.utils.PreferencesUtil
import com.ripenapps.ridechef.utils.makeTransparentStatusBar
import com.ripenapps.ridechef.view_model.SplashViewModel


@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {

    lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)
        splashTimer()
        return binding.root
    }

    private fun splashTimer() {
        val splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        splashViewModel.splashStatus.observe(viewLifecycleOwner, { status ->
            if (status) {
                val isUser = PreferencesUtil.getStringPreference(
                    requireContext(),
                    PrefConstants.IS_USER_LOGIN
                )

                if (isUser == "true") {
                    this.findNavController()
                        .navigate(SplashScreenDirections.actionSplashScreenToHomeScreen())
                } else {
                    this.findNavController()
                        .navigate(SplashScreenDirections.actionSplashScreenToAllowLocationScreen())
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        makeTransparentStatusBar(requireActivity(), true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        makeTransparentStatusBar(requireActivity(), false)
    }


}