package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.PersonalDetailsBottomSheetBinding
import com.ripenapps.ridechef.model.retrofit.models.LoginResponseData
import com.ripenapps.ridechef.model.retrofit.models.UpdateUserProfileRequest
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view_model.UserProfileVIewModel
import com.yaman.validations.validateName


class PersonalDetailsBottomSheet(val listener: (Int) -> Unit) : BottomSheetDialogFragment() {

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
            //Set UI.
            binding.userdata = res.response?.data
            //Set Object
            updateUserProfileRequest = UpdateUserProfileRequest(
                res.response?.data?.dob ?: "",
                res.response?.data?.email ?: "",
                res.response?.data?.name ?: "",
                null
            )
        }

        //Update Profile Response
        viewModel.updateUserProfileResponse.observe(this) { res ->
            if (res.response?.status == 200) {
                listener(res.response?.status!!)
                Toast.makeText(requireContext(), res.response?.message, Toast.LENGTH_SHORT).show()
            } else {
                Log.e("updateUserProfile", "Error: ${res.response?.message}")
                Log.e("updateUserProfile", "Error: ${res.errorBody}")
                Log.e("updateUserProfile", "Error: ${res.error}")
            }
        }
    }

    private fun setClicks() {

        binding.doneButton.setOnClickListener {

            val validName = validateName(binding.name.text.toString())

            if (!validName.validStatus) {
                Toast.makeText(requireContext(), validName.validReason, Toast.LENGTH_SHORT).show()
            } else {
                updateUserProfileRequest.name = binding.name.text.toString()
                Log.e("TAG", "setClicks Name: ${updateUserProfileRequest.name}")

                viewModel.callApiUpdateUserProfile(
                    userdata?.tokenType + " " + userdata?.accessToken,
                    updateUserProfileRequest
                )
            }
        }

    }

}