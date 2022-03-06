package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.futuremind.recyclerviewfastscroll.Utils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.PersonalDetailsBottomSheetBinding
import com.ripenapps.ridechef.model.retrofit.models.LoginResponseData
import com.ripenapps.ridechef.model.retrofit.models.UpdateUserProfileRequest
import com.ripenapps.ridechef.utils.PrefConstants
import com.ripenapps.ridechef.utils.PreferencesUtil
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.utils.permssion.openDatePicker
import com.ripenapps.ridechef.view_model.UserProfileVIewModel
import com.yaman.utils.getDateDifferenceFromCurrentDate
import com.yaman.validations.validateCustom
import com.yaman.validations.validateName
import java.util.*


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

            PreferencesUtil.setStringPreference(
                requireContext(),
                PrefConstants.USER_IMAGE,
                res.response?.data?.profileImage
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
            val validDob = validateCustom(binding.dob.text.toString(), "Please Select Date.")

            if (!validName.validStatus) {
                Toast.makeText(requireContext(), validName.validReason, Toast.LENGTH_SHORT).show()
            } else if (!validDob.validStatus) {
                Toast.makeText(requireContext(), validDob.validReason, Toast.LENGTH_SHORT).show()
            } else {
                updateUserProfileRequest.name = binding.name.text.toString()
                Log.e("TAG", "setClicks Name: ${updateUserProfileRequest.name}")

                viewModel.callApiUpdateUserProfile(
                    userdata?.tokenType + " " + userdata?.accessToken,
                    updateUserProfileRequest
                )
            }
        }

        binding.dob.setOnClickListener {
            openDatePicker(
                requireActivity(),
                isMinDate = true,
                listener = { dayOfMonth, monthOfYear, year ->
                    binding.dob.setText("$dayOfMonth-$monthOfYear-$year")
                    updateUserProfileRequest.dob = "$monthOfYear $dayOfMonth $year"
                })
        }


    }

}