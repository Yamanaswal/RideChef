package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentSideMenuScreenBinding
import com.ripenapps.ridechef.model.sideMenuList
import com.ripenapps.ridechef.utils.PrefConstants
import com.ripenapps.ridechef.utils.PreferencesUtil
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view_model.UserProfileVIewModel

class SideMenuScreen : Fragment() {

    lateinit var binding: FragmentSideMenuScreenBinding
    lateinit var viewModel: UserProfileVIewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_side_menu_screen, container, false)
        //Set Data in Side Menus
        binding.sideMenuDataList = sideMenuList
        viewModel = ViewModelProvider(this)[UserProfileVIewModel::class.java]
        setData()
        return binding.root
    }

    private fun setObservers() {
        viewModel.getUserProfileResponse.observe(this) {
            binding.getUserData = it.response?.data
        }
    }

    private fun setData() {

        val userdata = getUserData(requireContext())
        binding.mobileNo.text = userdata?.mobileNumber.toString()
        viewModel.callApiGetUserProfile(userdata?.tokenType + " " + userdata?.accessToken)

        ////Set Clicks
        binding.home.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.notification.sideMenuRow.setOnClickListener {
            if (userdata?.accessToken != null) {
                this.findNavController()
                    .navigate(SideMenuScreenDirections.actionSideMenuScreenToMyOrdersScreen())
            } else {
                this.findNavController()
                    .navigate(SideMenuScreenDirections.actionSideMenuScreenToNotificationScreen())
            }
        }

        binding.profileSettings.sideMenuRow.setOnClickListener {
            if (userdata?.accessToken != null) {
                this.findNavController()
                    .navigate(SideMenuScreenDirections.actionSideMenuScreenToMyOrdersScreen())
            } else {
                this.findNavController()
                    .navigate(SideMenuScreenDirections.actionSideMenuScreenToProfileSettingsScreen())
            }

        }

        binding.myFavorites.sideMenuRow.setOnClickListener {
            if (userdata?.accessToken != null) {
                this.findNavController()
                    .navigate(SideMenuScreenDirections.actionSideMenuScreenToMyOrdersScreen())
            } else {
                this.findNavController()
                    .navigate(SideMenuScreenDirections.actionSideMenuScreenToMyFavouriteScreen())
            }

        }

        binding.myOrders.sideMenuRow.setOnClickListener {
            if (userdata?.accessToken != null) {
                this.findNavController()
                    .navigate(SideMenuScreenDirections.actionSideMenuScreenToMyOrdersScreen())
            } else {
                this.findNavController()
                    .navigate(SideMenuScreenDirections.actionSideMenuScreenToLoginScreen())
            }

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

        binding.faq.sideMenuRow.setOnClickListener {
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToFaqsScreen())
        }

        binding.helpSupport.sideMenuRow.setOnClickListener {
            this.findNavController()
                .navigate(SideMenuScreenDirections.actionSideMenuScreenToHelpAndSupport())
        }

        binding.logout.sideMenuRow.setOnClickListener {
            removeLocalData()
            viewModel.callApiLogout(userdata?.tokenType + " " + userdata?.accessToken)
            this.findNavController().navigate(SideMenuScreenDirections.actionSideMenuScreenToLoginScreen())
        }


        setObservers()
    }

    private fun removeLocalData(){
        PreferencesUtil.removePreference(requireContext(), PrefConstants.USERDATA)
        PreferencesUtil.removePreference(requireContext(), PrefConstants.IS_USER_LOGIN)
        PreferencesUtil.removePreference(requireContext(), PrefConstants.IS_SKIPPED)
        PreferencesUtil.removePreference(requireContext(), PrefConstants.USER_IMAGE)
    }


    
}

