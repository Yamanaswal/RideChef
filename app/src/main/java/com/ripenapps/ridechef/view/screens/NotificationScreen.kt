package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentNotificationScreenBinding
import com.ripenapps.ridechef.view.adapters.NotificationsAdapter

class NotificationScreen : Fragment() {

    lateinit var binding: FragmentNotificationScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_notification_screen,
            container,
            false
        )
        setAppBar()
        setNotificationRecyclerView()
        return binding.root
    }

    private fun setNotificationRecyclerView() {
        val notificationsAdapter = NotificationsAdapter(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = notificationsAdapter
    }

    private fun setAppBar() {
        binding.appBarId.titleId.text = getString(R.string.notifications)
        binding.appBarId.backButtonId.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}