package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentMyFavouriteScreenBinding
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view.adapters.MyFavoritesRecyclerViewAdapter
import com.ripenapps.ridechef.view_model.MyFavouriteViewModel


class MyFavouriteScreen : Fragment() {

    lateinit var binding: FragmentMyFavouriteScreenBinding
    lateinit var viewModel: MyFavouriteViewModel

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
        viewModel.callApiMyFavourite(user?.tokenType + user?.accessToken)
        setMyFavouritesRecyclerView()

        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.getFavoriteRestaurantsResponse.observe(this) { res ->

        }
    }

    private fun setMyFavouritesRecyclerView() {
        val myFavoritesRecyclerViewAdapter = MyFavoritesRecyclerViewAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = myFavoritesRecyclerViewAdapter
    }


}