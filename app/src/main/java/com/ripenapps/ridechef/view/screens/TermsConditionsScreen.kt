package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentTermsConditionsScreenBinding


class TermsConditionsScreen : Fragment() {

    lateinit var binding: FragmentTermsConditionsScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_terms_conditions_screen,
            container,
            false
        )

        return binding.root
    }


}