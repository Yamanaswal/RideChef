package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.ripenapps.ridechef.databinding.FragmentChangeLocationBinding

import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.ripenapps.ridechef.R
import com.google.android.libraries.places.widget.AutocompleteSupportFragment


class ChangeLocation : Fragment() {

    val TAG = "ChangeLocation"

    lateinit var binding: FragmentChangeLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_location, container, false)
        setSearchAutoComplete()
        return binding.root
    }

    private fun setSearchAutoComplete() {
        // Initialize the Places.
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), R.string.google_maps_key.toString())
        }

        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.name + ", " + place.id)
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: $status")
            }
        })

    }

}