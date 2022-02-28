package com.ripenapps.ridechef.stripe_payment

import android.content.Context
import android.util.Log
import com.ripenapps.ridechef.R
import com.stripe.android.PaymentSession
import com.stripe.android.PaymentSessionConfig
import com.stripe.android.PaymentSessionData
import java.util.*
import kotlin.collections.HashSet
import com.stripe.android.model.*

class StripePayment(val context: Context) {

    private fun createPaymentSessionConfig(): PaymentSessionConfig {

        return PaymentSessionConfig.Builder()
            // hide the phone field on the shipping information form
            // make the address line 2 field optional
            // specify an address to pre-populate the shipping information form
            .setPrepopulatedShippingInfo(
                ShippingInformation(
                    Address.Builder()
                        .setLine1("123 Market St")
                        .setCity("San Francisco")
                        .setState("CA")
                        .setPostalCode("94107")
                        .setCountry("US")
                        .build(),
                    "Jenny Rosen",
                    "4158675309"
                )
            ) // collect shipping information
            .setShippingInfoRequired(true) // collect shipping method
            .setShippingMethodsRequired(true) // specify the payment method types that the customer can use;
            // defaults to PaymentMethod.Type.Card
            .setPaymentMethodTypes(
                listOf(PaymentMethod.Type.Card)
            ) // only allow US and Canada shipping addresses
            .setAllowedShippingCountryCodes(
                HashSet(
                    listOf("US", "CA")
                )
            ) // specify a layout to display under the payment collection form
            .setAddPaymentMethodFooter(R.layout.add_payment_method_footer) // specify the shipping information validation delegate
            .setShippingInformationValidator(AppShippingInformationValidator()) // specify the shipping methods factory delegate
            .setShippingMethodsFactory(AppShippingMethodsFactory) // if `true`, will show "Google Pay" as an option on the
            // Payment Methods selection screen
            .setShouldShowGooglePay(true)
            .build()
    }

    private class AppShippingInformationValidator :
        PaymentSessionConfig.ShippingInformationValidator {
        override fun isValid(
            shippingInformation: ShippingInformation
        ): Boolean {
            val address = shippingInformation.address
            return address != null && Locale.US.country === address.country
        }

        override fun getErrorMessage(
            shippingInformation: ShippingInformation
        ): String {
            return "A US address is required"
        }
    }

    companion object AppShippingMethodsFactory : PaymentSessionConfig.ShippingMethodsFactory {

        override fun create(shippingInformation: ShippingInformation): List<ShippingMethod> {
            return listOf(
                ShippingMethod(
                    "UPS Ground",
                    "ups-ground",
                    0,
                    "USD",
                    "Arrives in 3-5 days"
                ),
                ShippingMethod(
                    "FedEx",
                    "fedex",
                    599,
                    "USD",
                    "Arrives tomorrow"
                )
            )
        }

    }

}


class MyPaymentSessionListener : PaymentSession.PaymentSessionListener {

    val TAG = "PaymentSessionListener"

    override fun onCommunicatingStateChanged(isCommunicating: Boolean) {
        if (isCommunicating) {
            // update UI to indicate that network communication is in progress
        } else {
            // update UI to indicate that network communication has completed
        }
    }

    override fun onError(errorCode: Int, errorMessage: String) {
        Log.e(TAG, "onError: $errorCode")
        Log.e(TAG, "onError: $errorMessage")
//        Toast.makeText(context, errorMessage ?: "", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentSessionDataChanged(data: PaymentSessionData) {
        // Update your UI here with other data
        if (data.isPaymentReadyToCharge) {
            // Use the data to complete your charge - see below.
        }
    }

}
