package com.ripenapps.ridechef

import android.app.Application
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.InternetConnection

class ApplicationUtil : Application() {

    lateinit var internetConnection : InternetConnection

    override fun onCreate() {
        super.onCreate()
        internetConnection = InternetConnection(this)

//        internetConnection.observe(applicationContext) { i ->
//
//        }
    }

}