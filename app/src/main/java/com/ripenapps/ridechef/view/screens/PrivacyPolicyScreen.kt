package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentPrivacyPolicyBinding

class PrivacyPolicyScreen : Fragment() {

    lateinit var binding: FragmentPrivacyPolicyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_privacy_policy, container, false)
        setAppBar()
        return binding.root
    }

    private fun setAppBar() {
        binding.appBarId.titleId.text = "Privacy Policy"
        binding.appBarId.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}