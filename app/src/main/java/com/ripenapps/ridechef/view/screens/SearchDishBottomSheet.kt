package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentSearchDishBottomSheetBinding
import com.ripenapps.ridechef.model.retrofit.models.SearchDishInsideRestRequest
import com.ripenapps.ridechef.view.adapters.DishSearchBodyRecyclerViewAdapter
import com.ripenapps.ridechef.view_model.RestaurantDetailsViewModel

class SearchDishBottomSheet(private val merchant_id: Int?) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentSearchDishBottomSheetBinding
    lateinit var dishSearchRecyclerViewAdapter: DishSearchBodyRecyclerViewAdapter
    lateinit var viewModel: RestaurantDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search_dish_bottom_sheet,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[RestaurantDetailsViewModel::class.java]
        viewModel.callApiSearchDishInsideRest(
            SearchDishInsideRestRequest(
                merchant_id.toString(),
                ""
            )
        )
        setRecyclerView()
        setSearch()
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.dishInsideRestResponse.observe(this) { res ->
            dishSearchRecyclerViewAdapter.updateList(res.response?.data?.dataInsideRest)
        }
    }

    private fun setSearch() {
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length!! >= 2){
                    viewModel.callApiSearchDishInsideRest(
                        SearchDishInsideRestRequest(
                            merchant_id.toString(),
                            s.toString()
                        )
                    )
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun setRecyclerView() {
        dishSearchRecyclerViewAdapter = DishSearchBodyRecyclerViewAdapter(requireContext()) {

        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = dishSearchRecyclerViewAdapter
    }

}