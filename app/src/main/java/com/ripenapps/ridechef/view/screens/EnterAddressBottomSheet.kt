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
import com.google.gson.Gson
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.EnterAddressBottomsheetBinding
import com.ripenapps.ridechef.model.retrofit.models.EmptyResponse
import com.ripenapps.ridechef.model.retrofit.models.SaveUserAddressRequest
import com.ripenapps.ridechef.model.retrofit.models.UserAddressesData
import com.ripenapps.ridechef.utils.PrefConstants
import com.ripenapps.ridechef.utils.PreferencesUtil
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view_model.SavedAddressViewModel
import com.yaman.validations.validateCustom

class EnterAddressBottomSheet(
    private val args: ChangeLocationArgs,
    val listener: (EmptyResponse) -> Unit
) : BottomSheetDialogFragment() {

    lateinit var binding: EnterAddressBottomsheetBinding
    lateinit var viewModel: SavedAddressViewModel
    var savedAddressRequest = SaveUserAddressRequest()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.enter_address_bottomsheet,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[SavedAddressViewModel::class.java]

        if(args.requestType == "add"){
            setAddRequest()
        }else{
            setEditRequest()
        }

        setClicks()
        setObservers()
        return binding.root
    }

    private fun setEditRequest() {
        val editAddress = Gson().fromJson(args.editAddress,UserAddressesData::class.java)
        //Set Request Object.
        savedAddressRequest.requestType = args.requestType
        savedAddressRequest.default = "1"
        savedAddressRequest.type = editAddress.type.toString()
        savedAddressRequest.userAddressId = editAddress.id.toString()

        savedAddressRequest.fullAddress = editAddress.fullAddress
        savedAddressRequest.landmark = editAddress.landmark
        savedAddressRequest.pinCode = editAddress.pincode.toString()
        savedAddressRequest.default = editAddress.default.toString()

        savedAddressRequest.address = PreferencesUtil.getStringPreference(requireContext(), PrefConstants.ADDRESS) ?: ""
        savedAddressRequest.latitude = PreferencesUtil.getStringPreference(requireContext(), PrefConstants.LATITUDE) ?: ""
        savedAddressRequest.longitude = PreferencesUtil.getStringPreference(requireContext(), PrefConstants.LONGITUDE) ?: ""

        //Set Views
        binding.pinCode.setText(editAddress.pincode.toString())
        binding.houseNumber.setText(editAddress.fullAddress)
        binding.landmarkAddress.setText(editAddress.landmark)
    }

    private fun setAddRequest() {
        savedAddressRequest.requestType = args.requestType
        savedAddressRequest.address = PreferencesUtil.getStringPreference(requireContext(), PrefConstants.ADDRESS) ?: ""
        savedAddressRequest.latitude = PreferencesUtil.getStringPreference(requireContext(), PrefConstants.LATITUDE) ?: ""
        savedAddressRequest.longitude = PreferencesUtil.getStringPreference(requireContext(), PrefConstants.LONGITUDE) ?: ""
        savedAddressRequest.default = "1"
        savedAddressRequest.type = "1" //Home
        savedAddressRequest.userAddressId = ""
    }

    private fun setClicks() {
        val userdata = getUserData(requireContext())

        binding.saveLocation.setOnClickListener {
            val validFullAddress = validateCustom(
                binding.houseNumber.text.toString(),
                "Please Enter Complete Address."
            )
            val validPinCode = validateCustom(binding.pinCode.text.toString(), "Please Enter Pin Code.")

            //Set PinCode, Complete Address, Landmark
            savedAddressRequest.fullAddress = binding.houseNumber.text.toString()
            savedAddressRequest.landmark = binding.landmarkAddress.text.toString()
            savedAddressRequest.pinCode = binding.pinCode.text.toString()

            if (!validFullAddress.validStatus) {
                Toast.makeText(requireContext(), validFullAddress.validReason, Toast.LENGTH_SHORT)
                    .show()
            }

            if (!validPinCode.validStatus) {
                Toast.makeText(requireContext(), validPinCode.validReason, Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.callApiSaveUserAddress(
                    userdata?.tokenType + " " + userdata?.accessToken,
                    savedAddressRequest
                )
            }

        }

        binding.home.setOnClickListener {
            setHomeType()
        }

        binding.work.setOnClickListener {
            setWorkType()
        }

        binding.other.setOnClickListener {
            setOtherType()
        }

    }

    private fun setOtherType() {
        savedAddressRequest.type = "3"
        binding.other.setBackgroundResource(R.drawable.round_button_red_background)
        binding.other.setTextColor(resources.getColor(R.color.white))
        binding.home.setBackgroundResource(R.drawable.round_gray_11dp)
        binding.home.setTextColor(resources.getColor(R.color.black_80_op))
        binding.work.setBackgroundResource(R.drawable.round_gray_11dp)
        binding.work.setTextColor(resources.getColor(R.color.black_80_op))
    }

    private fun setWorkType() {
        savedAddressRequest.type = "2"
        binding.work.setBackgroundResource(R.drawable.round_button_red_background)
        binding.work.setTextColor(resources.getColor(R.color.white))
        binding.home.setBackgroundResource(R.drawable.round_gray_11dp)
        binding.home.setTextColor(resources.getColor(R.color.black_80_op))
        binding.other.setBackgroundResource(R.drawable.round_gray_11dp)
        binding.other.setTextColor(resources.getColor(R.color.black_80_op))
    }

    private fun setHomeType() {
        savedAddressRequest.type = "1"
        binding.home.setBackgroundResource(R.drawable.round_button_red_background)
        binding.home.setTextColor(resources.getColor(R.color.white))
        binding.work.setBackgroundResource(R.drawable.round_gray_11dp)
        binding.work.setTextColor(resources.getColor(R.color.black_80_op))
        binding.other.setBackgroundResource(R.drawable.round_gray_11dp)
        binding.other.setTextColor(resources.getColor(R.color.black_80_op))
    }

    private fun setObservers() {
        viewModel.saveUserAddressResponse.observe(this) { res ->
            if (res.response?.status == 200) {
                dismiss()
                listener(res.response!!)
            } else {
                Log.e("TAG Error", "setObservers: $res")
                Log.e("TAG Error", "setObservers: ${res.error}")
                Log.e("TAG Error", "setObservers: ${res.response}")
                Log.e("TAG Error", "setObservers: ${res.errorBody}")
            }

        }
    }
}