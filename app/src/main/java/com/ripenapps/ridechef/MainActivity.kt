package com.ripenapps.ridechef

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.ripenapps.ridechef.databinding.ActivityMainBinding
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.InternetConnection

class MainActivity : AppCompatActivity() {

    lateinit var internetConnection: InternetConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.myNavHostFragment)
        internetConnection = InternetConnection(this)
    }

}

