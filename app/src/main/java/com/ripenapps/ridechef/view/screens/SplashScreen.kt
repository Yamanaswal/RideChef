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
import android.os.Build

import androidx.appcompat.app.AppCompatActivity

import android.app.Activity
import android.graphics.Color
import android.util.Log
import java.util.*


@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {

    lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)
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
        requireActivity().window.decorView.fitsSystemWindows
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

//    fun transparentStatusBar(activity: Activity, isTransparent: Boolean, fullscreen: Boolean) {
//        if (isTransparent) {
//            activity.window.decorView.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            (Objects.requireNonNull(activity) as AppCompatActivity).supportActionBar!!.hide()
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                defaultStatusBarColor = activity.window.statusBarColor
//                activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                // FOR TRANSPARENT NAVIGATION BAR
//                //activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//                activity.window.statusBarColor = Color.TRANSPARENT
//                Log.d(
//                    "TAG",
//                    "Setting Color Transparent " + Color.TRANSPARENT.toString() + " Default Color " + defaultStatusBarColor
//                )
//            } else {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    Log.d("TAG", "Setting Color Trans " + Color.TRANSPARENT)
//                    activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                }
//            }
//        } else {
//            if (fullscreen) {
//                val decorView = activity.window.decorView
//                val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_FULLSCREEN)
//                decorView.systemUiVisibility = uiOptions
//            } else {
//                (Objects.requireNonNull(activity) as AppCompatActivity).supportActionBar!!.show()
//                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
//                    activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//                    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//                    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//                    activity.window.statusBarColor = defaultStatusBarColor
//                } else {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                    }
//                }
//            }
//        }
//    }
}