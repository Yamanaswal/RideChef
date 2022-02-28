package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.PersonalDetailsBottomSheetBinding
import com.ripenapps.ridechef.model.retrofit.models.LoginResponseData
import com.ripenapps.ridechef.model.retrofit.models.UpdateUserProfileRequest
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view_model.UserProfileVIewModel

class PersonalDetailsBottomSheet : Fragment() {

    lateinit var binding: PersonalDetailsBottomSheetBinding
    lateinit var viewModel: UserProfileVIewModel
    var userdata: LoginResponseData? = null
    lateinit var updateUserProfileRequest: UpdateUserProfileRequest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.personal_details_bottom_sheet,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[UserProfileVIewModel::class.java]
        userdata = getUserData(requireContext())
        viewModel.callApiGetUserProfile(userdata?.tokenType + " " + userdata?.accessToken)

        setClicks()
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        //Get User Data
        viewModel.getUserProfileResponse.observe(this) { res ->

        }

        //Update Profile Response
        viewModel.updateUserProfileResponse.observe(this) { res ->

        }
    }

    private fun setClicks() {

        binding.doneButton.setOnClickListener {
            viewModel.callApiUpdateUserProfile(
                userdata?.tokenType + " " + userdata?.accessToken,
                updateUserProfileRequest
            )
        }

    }

}