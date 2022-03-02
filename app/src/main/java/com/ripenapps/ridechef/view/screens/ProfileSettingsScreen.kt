package com.ripenapps.ridechef.view.screens

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentProfileSettingsScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.LoginResponseData
import com.ripenapps.ridechef.model.retrofit.models.UpdateUserProfileRequest
import com.ripenapps.ridechef.utils.ImageDialogPicker
import com.ripenapps.ridechef.utils.ImagePickerInterface
import com.ripenapps.ridechef.utils.getImagePath
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view_model.UserProfileVIewModel
import com.yaman.validations.validateName

class ProfileSettingsScreen : Fragment() {

    lateinit var binding: FragmentProfileSettingsScreenBinding
    lateinit var viewModel: UserProfileVIewModel
    lateinit var updateUserProfileRequest: UpdateUserProfileRequest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile_settings_screen,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[UserProfileVIewModel::class.java]
        val userData = getUserData(requireContext())
        viewModel.callApiGetUserProfile(userData?.tokenType + " " + userData?.accessToken)

        setAppBar()
        setClicks(userData)
        setObservers()
        return binding.root
    }

    private fun setClicks(userData: LoginResponseData?) {

        binding.saveChanges.setOnClickListener {
            val validName = validateName(binding.name.text.toString())

            if (!validName.validStatus) {
                Snackbar.make(requireView(), validName.validReason, 2000).show()
            } else {
                updateUserProfileRequest.name = binding.name.text.toString()
                updateUserProfileRequest.email = binding.email.text.toString()

                viewModel.callApiUpdateUserProfile(
                    userData?.tokenType + " " + userData?.accessToken,
                    updateUserProfileRequest = updateUserProfileRequest
                )
            }
        }

        binding.personImage.setOnClickListener {
            val imageDialogPicker = ImageDialogPicker(object : ImagePickerInterface {
                override fun getUri(uri: Uri?) {
                    Log.e("TAG", "getUri: $uri")
                    binding.profileImage.setImageURI(uri)

                    if (uri != null) {
                        val path = getImagePath(requireContext(), uri)
                        if (path != null) {
                            updateUserProfileRequest.profileImage = path
                        }
                    }
                }
            })
            imageDialogPicker.show(childFragmentManager, "imageDialogPicker")
        }
    }

    private fun setObservers() {
        //Get Profile Data
        viewModel.getUserProfileResponse.observe(this) { res ->
            if (res.response?.status!! == 200) {
                binding.userdata = res.response?.data

                updateUserProfileRequest = UpdateUserProfileRequest(
                    res.response?.data?.dob ?: "",
                    res.response?.data?.email ?: "",
                    res.response?.data?.name ?: "",
                    null
                )
            } else {
                Toast.makeText(requireContext(), res.response?.message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.updateUserProfileResponse.observe(this) { res ->
            if (res.response?.status == 200) {
                Snackbar.make(requireView(), res.response?.message.toString(), 1500).show()
            }else{

            }
        }
    }

    private fun setAppBar() {
        binding.appBarId.titleId.text = "Profile Settings"
        binding.appBarId.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}