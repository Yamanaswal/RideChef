package com.ripenapps.ridechef.stripe_payment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.ripenapps.ridechef.R
import com.stripe.android.PaymentSessionConfig

import com.stripe.android.PaymentSession
import com.stripe.android.model.ShippingInformation
import com.stripe.android.model.PaymentMethod
import com.stripe.android.PaymentSessionData


class StripePaymentHostActivity : AppCompatActivity() {

    val TAG = "StripePaymentHost"

    private val startPaymentFlowButton: Button? = null
    private var paymentSession: PaymentSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stripe_payment_host)

        paymentSession = PaymentSession(
            this,
            PaymentSessionConfig.Builder()
                .setPrepopulatedShippingInfo(getDefaultShippingInfo())
                .build()
        )
        setupPaymentSession()
    }

    private fun setupPaymentSession() {

        paymentSession!!.init(
            object : PaymentSession.PaymentSessionListener {
                override fun onCommunicatingStateChanged(
                    isCommunicating: Boolean
                ) {
                    // update UI, such as hiding or showing a progress bar
                }

                override fun onError(
                    errorCode: Int,
                    errorMessage: String
                ) {
                    // handle error
                    Log.e(TAG, "onError: $errorCode")
                    Log.e(TAG, "onError: $errorMessage")
                    Toast.makeText(
                        this@StripePaymentHostActivity,
                        errorMessage ?: "",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPaymentSessionDataChanged(
                    data: PaymentSessionData
                ) {
                    val paymentMethod = data.paymentMethod
                    // use paymentMethod
                }
            }
        )

        startPaymentFlowButton!!.isEnabled = true
    }

    private fun getDefaultShippingInfo(): ShippingInformation {
        // optionally specify default shipping address
        return ShippingInformation()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            paymentSession?.handlePaymentData(requestCode, resultCode, data);
        }
    }

}