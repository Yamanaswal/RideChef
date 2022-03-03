package com.ripenapps.ridechef.view.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentPaymentScreenBinding
import com.ripenapps.ridechef.model.retrofit.models.PlaceOrderRequest
import com.ripenapps.ridechef.utils.getUserData
import com.ripenapps.ridechef.view_model.PaymentViewModel
import com.stripe.android.ApiResultCallback
import com.stripe.android.Stripe
import com.stripe.android.model.PaymentMethod
import com.stripe.android.model.PaymentMethodCreateParams
import java.lang.Exception

class PaymentScreen : Fragment() {

    lateinit var binding: FragmentPaymentScreenBinding
    lateinit var viewModel: PaymentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_screen, container, false)
        viewModel = ViewModelProvider(this)[PaymentViewModel::class.java]

        setClicks()
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.placeOrderResponse.observe(this) { res ->
            if (res.response?.status == 200) {
                this.findNavController().navigate(PaymentScreenDirections.actionPaymentScreenToOrderPlacedSuccessScreen(
                            res.response?.data?.orderId!!
                        )
                    )
            }
        }

    }

    private fun setClicks() {
        binding.pay.setOnClickListener {
            stripePayment()
        }
    }


    private fun stripePayment() {

        val userData = getUserData(requireContext())
        val paymentMethodCreateParams: PaymentMethodCreateParams? =
            binding.cardInputWidget.paymentMethodCreateParams

        if (paymentMethodCreateParams != null) {

            val stripe = Stripe(
                requireContext(),
                resources.getString(R.string.stripe_development_key), enableLogging = true
            )

            //start dialog
            stripe.createPaymentMethod(
                paymentMethodCreateParams,
                null,
                null,
                object : ApiResultCallback<PaymentMethod> {
                    override fun onSuccess(result: PaymentMethod) {
                        Log.e("createPaymentMethod", "onSuccess: $result")

                        viewModel.callApiPlaceOrder(
                            userData?.tokenType + " " + userData?.accessToken,
                            PlaceOrderRequest(
                                addressId = "1",
                                merchantId = "2",
                                price = "200",
                                finalPrice = "500",
                                riderTip = "6",
                                tax = "4",
                                deliveryCharge = "10",
                                paymentMethod = result.id ?: ""
                            )
                        )

                    }

                    override fun onError(e: Exception) {
                        //Dismiss Dialog
                        Log.e("createPaymentMethod", "onSuccess: $e")
                    }
                })
        }
    }

}