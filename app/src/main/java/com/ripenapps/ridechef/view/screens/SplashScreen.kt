package com.ripenapps.ridechef.view.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentSplashScreenBinding
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
                this.findNavController()
                    .navigate(SplashScreenDirections.actionSplashScreenToAllowLocationScreen())
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

}