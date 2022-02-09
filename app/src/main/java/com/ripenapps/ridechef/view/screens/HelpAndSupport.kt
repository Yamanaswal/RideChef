package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentHelpAndSupportBinding

class HelpAndSupport : Fragment() {

    lateinit var binding: FragmentHelpAndSupportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_help_and_support, container, false)

        setAppBar()
        return binding.root
    }

    private fun setAppBar() {
        binding.appBarId.titleId.text = getString(R.string.help_support)
        binding.appBarId.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}