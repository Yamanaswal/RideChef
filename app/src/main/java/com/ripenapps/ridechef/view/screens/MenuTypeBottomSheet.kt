package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentMenuTypeBottomSheetBinding
import com.ripenapps.ridechef.model.retrofit.models.MerchantMenuType
import com.ripenapps.ridechef.view.adapters.MenuTypeRecyclerViewAdapter

class MenuTypeBottomSheet(private val merchantMenuTypes: List<MerchantMenuType>?) :
    BottomSheetDialogFragment() {

    lateinit var binding: FragmentMenuTypeBottomSheetBinding
    lateinit var menuTypeRecyclerViewAdapter: MenuTypeRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_menu_type_bottom_sheet,
            container,
            false
        )

        setRecyclerView()
        return binding.root
    }

    private fun setRecyclerView() {
        menuTypeRecyclerViewAdapter = MenuTypeRecyclerViewAdapter() {

        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = menuTypeRecyclerViewAdapter
        menuTypeRecyclerViewAdapter.updateList(merchantMenuTypes)
    }

}