package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentMyFavouriteScreenBinding
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view.adapters.MyFavoritesRecyclerViewAdapter
import com.ripenapps.ridechef.view_model.MyFavouriteViewModel
import com.google.android.gms.common.data.DataHolder
import com.ripenapps.ridechef.model.retrofit.models.MyFavouriteResponseData
import java.util.*


class MyFavouriteScreen : Fragment() {

    lateinit var binding: FragmentMyFavouriteScreenBinding
    lateinit var viewModel: MyFavouriteViewModel
    lateinit var myFavoritesRecyclerViewAdapter: MyFavoritesRecyclerViewAdapter
    private val myFavList = mutableListOf<MyFavouriteResponseData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_my_favourite_screen,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[MyFavouriteViewModel::class.java]
        val user = getUserData(requireContext())
        viewModel.callApiMyFavourite(user?.tokenType + " " + user?.accessToken)
        setClicks()
        setMyFavouritesRecyclerView()
        setObservers()
        return binding.root
    }

    private fun setClicks() {
        binding.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                if(editable.toString().trim().isNotEmpty()){
                    filter(editable.toString())
                }else{
                    myFavoritesRecyclerViewAdapter.updateList(myFavList)
                }
            }
        })

    }

    private fun filter(searchText: String) {
        //product.
        val filterList: MutableList<MyFavouriteResponseData> = ArrayList<MyFavouriteResponseData>()
        for (restItem in myFavList) {
            //for question name
            if (restItem.favoriteMerchant.restaurantName.lowercase().contains(searchText)) {
                filterList.add(restItem)
            }
        }
        //update recyclerview
        myFavoritesRecyclerViewAdapter.updateList(filterList)
    }

    private fun setObservers() {
        viewModel.getFavoriteRestaurantsResponse.observe(this) { res ->
            myFavoritesRecyclerViewAdapter.updateList(res.response?.data)
            myFavList.clear()
            myFavList.addAll(res.response?.data!!)
        }
    }

    private fun setMyFavouritesRecyclerView() {
        myFavoritesRecyclerViewAdapter = MyFavoritesRecyclerViewAdapter {
            this.findNavController().navigate(
                MyFavouriteScreenDirections.actionMyFavouriteScreenToRestaurantDetailsScreen(it.favoriteMerchant.id)
            )
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = myFavoritesRecyclerViewAdapter
    }


}