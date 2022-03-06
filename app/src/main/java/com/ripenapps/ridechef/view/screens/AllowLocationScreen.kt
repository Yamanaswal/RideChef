package com.ripenapps.ridechef.view.screens

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentAllowLocationScreenBinding
import com.ripenapps.ridechef.utils.PrefConstants
import com.ripenapps.ridechef.utils.PreferencesUtil
import com.ripenapps.ridechef.utils.permssion.PermissionManager
import com.ripenapps.ridechef.utils.permssion.PermissionResult
import com.yaman.location_services.GpsUtils
import com.yaman.location_services.OnGpsListener
import com.yaman.location_services.getAddressFromLatLng
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AllowLocationScreen : Fragment() {

    lateinit var binding: FragmentAllowLocationScreenBinding
    val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    var locationPermissionGranted = false

    var latitude = ""
    var longitude = ""
    var fullAddress = ""

    // The entry point to the Fused Location Provider.
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    val TAG = "AllowLocationScreen"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_allow_location_screen,
            container,
            false
        )

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        //Ask Permissions
        askLocationPermission()

        //get Current Location
        getDeviceCurrentLocation()

        binding.confirmLocation.setOnClickListener {
            setLatLongAddress()
            this.findNavController()
                .navigate(AllowLocationScreenDirections.actionAllowLocationScreenToLoginScreen())
        }

        binding.changeLocation.setOnClickListener {
            this.findNavController()
                .navigate(AllowLocationScreenDirections.actionAllowLocationScreenToChangeLocation())
        }

        return binding.root
    }

    /* TODO - GET LOCATION PERMISSIONS. */
    private fun askLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }

        GpsUtils(requireContext()).turnGPSOn(object : OnGpsListener {
            override fun gpsStatus(isGPSEnable: Boolean) {
                if (!isGPSEnable) {
                    askLocationPermission()
                }
            }
        })


        askPermissions()


        getDeviceCurrentLocation()
        
    }

    private fun askPermissions() {
        GlobalScope.launch {

            //Resume coroutine once result is ready
            when(PermissionManager.requestPermissions(this@AllowLocationScreen,1,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                is PermissionResult.PermissionGranted -> {
                    //Add your logic here after user grants permission(s)
                }
                is PermissionResult.PermissionDenied -> {
                    //Add your logic to handle permission denial
                    //Ask Again Permission
                    askPermissions()
                }
                is PermissionResult.PermissionDeniedPermanently -> {
                    //Add your logic here if user denied permission(s) permanently.
                    //Ideally you should ask user to manually go to settings and enable permission(s)
                }
                is PermissionResult.ShowRational -> {
                    //If user denied permission frequently then she/he is not clear about why you are asking this permission.
                    //This is your chance to explain them why you need permission.
                }
            }

        }

    }

//     Use registerForActivityResult(ActivityResultContract, ActivityResultCallback)
//     passing in a androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions object
//     for the ActivityResultContract
//     and handling the result in the callback.


    //TODO - GET CURRENT LOCATION.
    private fun getDeviceCurrentLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient!!.lastLocation
                locationResult.addOnCompleteListener(requireActivity(),
                    OnCompleteListener { task: Task<Location> ->
                        if (task.isSuccessful) {
                            // Set the map's camera position to the current location of the device.
                            if (task.result != null) {
                                Log.d(TAG, "getDeviceCurrentLocation: ${task.result}")
                                val address: Address? = getAddressFromLatLng(
                                    requireContext(),
                                    task.result.latitude,
                                    task.result.longitude
                                )

                                if (address != null) {
                                    binding.currentLocation.text = address.getAddressLine(0)
                                    //set Current Location in Prefs
                                    latitude = address.latitude.toString()
                                    longitude = address.latitude.toString()
                                    fullAddress = address.getAddressLine(0) ?: ""
                                }
                            }
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.")
                            Log.e(TAG, "Exception: %s ${task.exception}")
                        }
                    })
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message.toString())
            Toast.makeText(requireContext(), "location is not available.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun setLatLongAddress() {
        PreferencesUtil.setStringPreference(requireContext(), PrefConstants.LATITUDE, latitude)
        PreferencesUtil.setStringPreference(requireContext(), PrefConstants.LONGITUDE, longitude)
        PreferencesUtil.setStringPreference(requireContext(), PrefConstants.ADDRESS, fullAddress)
    }

}