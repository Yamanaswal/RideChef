package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.EnterAddressBottomsheetBinding
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view_model.SavedAddressViewModel

class EnterAddressBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: EnterAddressBottomsheetBinding
    lateinit var viewModel: SavedAddressViewModel

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

//        viewModel.callApiDishDetails(
//            dishDetailsRequest = DishDetailsRequest(
//                menuId = menu.id,
//                merchantId = menu.merchantId
//            )
//        )

        setClicks()
        setObservers()
        return binding.root
    }

    private fun setClicks() {
        val userdata = getUserData(requireContext())
        binding.saveLocation.setOnClickListener {
//            viewModel.callApiSaveUserAddress(userdata?.tokenType + " " + userdata?.accessToken)
        }
    }

    private fun setObservers() {

    }
}