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
import com.ripenapps.ridechef.utils.PrefConstants
import com.ripenapps.ridechef.utils.PreferencesUtil
import com.ripenapps.ridechef.utils.getUserData

class SideMenuScreen : Fragment() {

    lateinit var binding: FragmentSideMenuScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_side_menu_screen, container, false)
        //Set Data in Side Menus
        binding.sideMenuDataList = sideMenuList
        setData()
        return binding.root
    }

    private fun setData() {

        val userdata = getUserData(requireContext())

        ////Set Clicks
        binding.home.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.notification.sideMenuRow.setOnClickListener {
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToNotificationScreen())
        }

        binding.profileSettings.sideMenuRow.setOnClickListener {
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToProfileSettingsScreen())
        }

        binding.myFavorites.sideMenuRow.setOnClickListener {
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToMyFavouriteScreen())
        }

        binding.myOrders.sideMenuRow.setOnClickListener {
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToMyOrdersScreen())
        }

        binding.savedAddress.sideMenuRow.setOnClickListener {
            if (userdata?.accessToken != null) {
                this.findNavController()
                    .navigate(SideMenuScreenDirections.actionSideMenuScreenToSavedAddressScreen())
            } else {
                this.findNavController()
                    .navigate(SideMenuScreenDirections.actionSideMenuScreenToLoginScreen())
            }
        }

        binding.aboutUs.sideMenuRow.setOnClickListener {
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToAboutUsScreen())
        }

        binding.termsConditions.sideMenuRow.setOnClickListener {
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToTermsConditionsScreen())
        }

        binding.privacyPolicy.sideMenuRow.setOnClickListener {
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToPrivacyPolicyScreen())
        }

        binding.helpSupport.sideMenuRow.setOnClickListener {
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToPrivacyPolicyScreen())
        }

        binding.helpSupport.sideMenuRow.setOnClickListener {
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToHelpAndSupport())
        }

        binding.logout.sideMenuRow.setOnClickListener {
            PreferencesUtil.removePreference(requireContext(), PrefConstants.USERDATA)
            PreferencesUtil.removePreference(requireContext(), PrefConstants.IS_USER_LOGIN)
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToLoginScreen())
        }


    }


}

