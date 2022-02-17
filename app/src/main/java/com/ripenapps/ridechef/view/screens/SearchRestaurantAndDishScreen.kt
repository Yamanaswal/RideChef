package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentSearchRestaurantAndDishScreenBinding
import com.ripenapps.ridechef.databinding.FragmentSearchRestaurantAndDishScreenBindingImpl

class SearchRestaurantAndDishScreen : Fragment() {

    lateinit var binding: FragmentSearchRestaurantAndDishScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search_restaurant_and_dish_screen,
            container,
            false
        )
        setOnTabSelectedListeners()
        return binding.root
    }


    private fun setOnTabSelectedListeners() {
        //First Fragment
        replaceFragment(fragment = RestaurantSearchScreen())

        binding.tabLayoutId.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> replaceFragment(fragment = RestaurantSearchScreen())
                        1 -> replaceFragment(fragment = DishSearchScreen())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.tabLayoutContainer, fragment)
        transaction.commit()
    }

}