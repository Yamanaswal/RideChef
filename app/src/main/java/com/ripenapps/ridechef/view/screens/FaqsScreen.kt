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
import com.ripenapps.ridechef.databinding.FragmentFaqsScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.FaqRequest
import com.ripenapps.ridechef.view.adapters.FaqsAdapter
import com.ripenapps.ridechef.view_model.CmsViewModel


class FaqsScreen : Fragment() {

    lateinit var binding: FragmentFaqsScreenBinding
    lateinit var viewModel: CmsViewModel
    lateinit var faqsAdapter: FaqsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_faqs_screen, container, false)
        setAppBar()
        viewModel = ViewModelProvider(this)[CmsViewModel::class.java]
        viewModel.callApiFaq(FaqRequest(search = ""))

        setRecyclerView()
        setObservers()
        return binding.root
    }

    private fun setRecyclerView() {
        faqsAdapter = FaqsAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = faqsAdapter
    }

    private fun setObservers() {
        viewModel.faqResponse.observe(viewLifecycleOwner) { res ->
            if (res.response?.status!! == 200) {
                faqsAdapter.updateList(res.response?.data?.data)
            }
        }
    }

    private fun setAppBar() {
        binding.appBarId.titleId.text = getString(R.string.faqs)
        binding.appBarId.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}