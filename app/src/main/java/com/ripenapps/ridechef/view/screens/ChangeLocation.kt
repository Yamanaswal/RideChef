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
import androidx.navigation.fragment.navArgs
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.ripenapps.ridechef.databinding.FragmentChangeLocationBinding

import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.ripenapps.ridechef.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.ripenapps.ridechef.utils.PrefConstants
import com.ripenapps.ridechef.utils.PreferencesUtil
import com.yaman.location_services.GpsUtils
import com.yaman.location_services.OnGpsListener
import com.yaman.location_services.getAddressFromLatLng

class ChangeLocation : Fragment(), OnMapReadyCallback, OnMyLocationButtonClickListener,
    OnCameraIdleListener, OnCameraMoveListener {

    val TAG = "ChangeLocation"
    private val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 123

    lateinit var binding: FragmentChangeLocationBinding
    private val args: ChangeLocationArgs by navArgs()
    private lateinit var googleMap: GoogleMap
    var marker: Marker? = null

    var latitude = ""
    var longitude = ""
    var fullAddress = ""


    // The entry point to the Fused Location Provider.
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null

    private val DEFAULT_ZOOM = 20
    var locationPermissionGranted = false
    private var lastKnownLocation: Location? = null

    private val defaultLocation = LatLng(-33.8523341, 151.2106085)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_location, container, false)

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        //Set Map
        val mapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        askLocationPermission()

        //Set Places Api
        setSearchAutoComplete()

        // Get the current location of the device and set the position of the map.
        getDeviceCurrentLocation()


        binding.confirmLocation.setOnClickListener {
            //set Location
            setLatLongAddress()
            //Go to Login Screen
            when (args.requestType) {
                "add" -> {
                    val bottomSheet = EnterAddressBottomSheet(args) { res ->
                        if (res.status == 200) {
                            requireActivity().onBackPressed()
                        } else {
                            Toast.makeText(requireContext(), res.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    bottomSheet.show(parentFragmentManager, "EnterAddressBottomSheet")
                }
                "update" -> {
                    val bottomSheet = EnterAddressBottomSheet(args) { res ->
                        if (res.status == 200) {
                            requireActivity().onBackPressed()
                        } else {
                            Toast.makeText(requireContext(), res.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    bottomSheet.show(parentFragmentManager, "EnterAddressBottomSheet")
                }
                else -> {
                    this.findNavController()
                        .navigate(ChangeLocationDirections.actionChangeLocationToLoginScreen())

                }
            }
        }

        return binding.root
    }


    private fun setSearchAutoComplete() {
        // Initialize the Places.
        Places.initialize(requireActivity().applicationContext, getString(R.string.google_maps_key))

        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.LAT_LNG
            )
        )

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.name + ", " + place.id)
                binding.locationText.text = place.address
                setMarker()
                latitude = place.latLng?.latitude.toString()
                longitude = place.latLng?.longitude.toString()
                fullAddress = place.address ?: ""
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: ${status.statusCode}")
                Log.i(TAG, "An error occurred: ${status.statusMessage}")
            }
        })

    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        if (this::googleMap.isInitialized) {

            //Set Listeners
            this.googleMap.setOnMyLocationButtonClickListener(this)
            this.googleMap.setOnCameraIdleListener(this)
            this.googleMap.setOnCameraMoveListener(this)

            // Add a marker in Sydney and move the camera
            val sydney = LatLng(-34.00, 151.00)

            this.googleMap.addMarker(
                MarkerOptions()
                    .position(sydney)
                    .title("Marker in Sydney")
            )
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        return false
    }


    override fun onCameraIdle() {
        if (marker != null) {
            val address: Address? = getAddressFromLatLng(
                requireContext(),
                marker!!.position.latitude,
                marker!!.position.longitude
            )

            if (address != null) {
                Log.d(TAG, "Address: $address")
                //save address objects.
                binding.locationText.text = address.getAddressLine(0)

                setMarker()
                latitude = address.latitude.toString()
                longitude = address.latitude.toString()
                fullAddress = address.getAddressLine(0) ?: ""
            }
        }

        Log.e(TAG, "onCameraIdle: $fullAddress + $longitude + $latitude")
    }

    private fun setLatLongAddress() {
        PreferencesUtil.setStringPreference(requireContext(), PrefConstants.LATITUDE, latitude)
        PreferencesUtil.setStringPreference(requireContext(), PrefConstants.LONGITUDE, longitude)
        PreferencesUtil.setStringPreference(requireContext(), PrefConstants.ADDRESS, fullAddress)
    }

    override fun onCameraMove() {
        Log.d(TAG, "onCameraMove..")
        //set marker on current location.
        setMarker()
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

        if (this::googleMap.isInitialized) {
            updateLocationUI()
        }
    }


    /* TODO - get LOCATION CALLBACK. */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        locationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true
                } else {
                    askLocationPermission()
                }

            }
        }
    }


    private fun updateLocationUI() {

        try {
            if (locationPermissionGranted) {
                googleMap.isMyLocationEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true

                //Enable Location
                GpsUtils(requireContext()).turnGPSOn(object : OnGpsListener {
                    override fun gpsStatus(isGPSEnable: Boolean) {
                        if (isGPSEnable) {
                            getDeviceCurrentLocation()
                        } else {
                            updateLocationUI()
                        }
                    }
                })

            } else {
                googleMap.isMyLocationEnabled = false
                googleMap.uiSettings.isMyLocationButtonEnabled = false
                askLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message!!)
        }
    }


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
                            lastKnownLocation = task.result
                            if (lastKnownLocation != null) {

                                //move camera to current location.
                                googleMap.moveCamera(
                                    CameraUpdateFactory.newLatLngZoom(
                                        LatLng(
                                            lastKnownLocation!!.latitude,
                                            lastKnownLocation!!.longitude
                                        ),
                                        DEFAULT_ZOOM.toFloat()
                                    )
                                )

                                //set marker on current location.
                                marker = googleMap.addMarker(
                                    MarkerOptions().position(
                                        LatLng(
                                            lastKnownLocation!!.latitude,
                                            lastKnownLocation!!.longitude
                                        )
                                    ).icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_RED
                                        )
                                    ).draggable(false)
                                )

                            }
                            Log.d(TAG, "getDeviceCurrentLocation: $lastKnownLocation")
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.")
                            Log.e(TAG, "Exception: %s ${task.exception}")
                            googleMap.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    defaultLocation,
                                    DEFAULT_ZOOM.toFloat()
                                )
                            )
                            googleMap.uiSettings.isMyLocationButtonEnabled = false
                        }
                    })
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message.toString())
            Toast.makeText(requireContext(), "location is not available.", Toast.LENGTH_SHORT)
                .show()
        }
    }


    fun setMarker() {
        if (marker != null) {
            marker!!.position = googleMap.cameraPosition.target
            Log.w(
                TAG,
                "onMarkerDragEnd: " + marker.toString()
            )
        }
    }


}