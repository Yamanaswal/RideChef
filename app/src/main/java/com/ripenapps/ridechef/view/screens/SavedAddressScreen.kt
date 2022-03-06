package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentSavedAddressScreenBinding
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view.adapters.AddressButtonType
import com.ripenapps.ridechef.view.adapters.SavedAddressRecyclerViewAdapter
import com.ripenapps.ridechef.view_model.SavedAddressViewModel


class SavedAddressScreen : Fragment() {

    lateinit var binding: FragmentSavedAddressScreenBinding
    lateinit var viewModel: SavedAddressViewModel
    lateinit var savedAddressRecyclerAdapter: SavedAddressRecyclerViewAdapter
    val args: SavedAddressScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_saved_address_screen,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[SavedAddressViewModel::class.java]
        val userData = getUserData(requireContext())
        viewModel.callApiUserAddress(userData?.tokenType + " " + userData?.accessToken)
        setAppBar()
        setRecyclerView()
        setClicks()
        setObservers()
        return binding.root
    }

    private fun setClicks() {

        // Add New Address
        binding.addAddressButton.setOnClickListener {
            this.findNavController().navigate(
                SavedAddressScreenDirections.actionSavedAddressScreenToChangeLocation()
                    .setRequestType("add")
            )
        }


    }

    private fun setObservers() {
        viewModel.userAddressResponse.observe(this) { res ->
            if (res.response?.status == 200) {
                savedAddressRecyclerAdapter.updateList(res.response?.data)
            }
        }
    }

    private fun setRecyclerView() {
        savedAddressRecyclerAdapter =
            SavedAddressRecyclerViewAdapter { editAddressItem, buttonType ->
                if (buttonType == AddressButtonType.Edit) {
                    this.findNavController().navigate(
                        SavedAddressScreenDirections.actionSavedAddressScreenToChangeLocation()
                            .setRequestType("update")
                            .setEditAddress(Gson().toJson(editAddressItem))
                    )
                } else {
                    if (args.change) {
                        this.findNavController().popBackStack(R.id.myCartScreen,true)
                    }
                }
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = savedAddressRecyclerAdapter
    }


    private fun setAppBar() {
        binding.appBarId.titleId.text = getString(R.string.saved_address)
        binding.appBarId.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}