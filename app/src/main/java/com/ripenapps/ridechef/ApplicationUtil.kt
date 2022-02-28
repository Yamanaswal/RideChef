package com.ripenapps.ridechef

import android.app.Application
import com.ripenapps.ridechef.model.retrofit.retrofit_helper.InternetConnection
import com.stripe.android.PaymentConfiguration

class ApplicationUtil : Application() {

    lateinit var internetConnection : InternetConnection

    override fun onCreate() {
        super.onCreate()
        PaymentConfiguration.init(
            applicationContext,
            "pk_test_51K9bN4HMUfVn5kyKLy8dxISU3UALWyDzn6upbpLTZlFtMR28Jxzbzd5KQRaJWFqShlp96cqFo0b4S0TyJwiq4cEu00gf3y4jdb"
        )
    }

}