package com.ripenapps.ridechef.view.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentAboutUsScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.CmsRequest
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view_model.CmsViewModel


class AboutUsScreen : Fragment() {

    lateinit var binding: FragmentAboutUsScreenBinding
    lateinit var viewModel: CmsViewModel

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_us_screen, container, false)
        setAppBar()
        viewModel = ViewModelProvider(this) [CmsViewModel::class.java]
        val user = getUserData(requireContext())
        viewModel.callApiCmsData(user?.tokenType + " " + user?.accessToken, CmsRequest(type = 1))
        viewModel.cmsResponse.observe(this) {
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.loadData(it.response?.data?.content!!,"text/html","utf-8")
        }
        return binding.root
    }

    private fun setAppBar() {
        binding.appBarId.titleId.text = getString(R.string.about_us)
        binding.appBarId.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}