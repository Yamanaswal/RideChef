package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentSideMenuScreenBinding
import com.ripenapps.ridechef.model.sideMenuList

class SideMenuScreen : Fragment() {

    lateinit var binding: FragmentSideMenuScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_side_menu_screen, container, false)
        binding.sideMenuDataList = sideMenuList
        setData()
        return binding.root
    }

    private fun setData() {

        /// Home Button Click
        binding.home.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.notification.sideMenuRow.setOnClickListener {
//            this.findNavController().navigate()
        }







    }


}

