package com.tecent.paymentrazorpay

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import com.tecent.paymentrazorpay.databinding.ActivityMainBinding
import org.json.JSONObject


class MainActivity : AppCompatActivity(), PaymentResultWithDataListener {
    val TAG = "gateway"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        /**
         * Preload payment resources
         */
        Checkout.preload(applicationContext)

        binding.btnPay.setOnClickListener {
            startPayment()
        }
    }

    fun startPayment() {
        /**
         * Instantiate Checkout
         */
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_FIyDsosGNRebtc")
        /**
         * Set your logo here
         */
//        checkout.setImage(R.drawable.logo)
        /**
         * Reference to current activity
         */
        val activity: Activity = this
        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            val options = JSONObject()
            options.put("name", "Tecent")
            options.put("description", "Test order")
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
//            options.put("order_id", "order_DBJOWzybf0sJbb") //from response of step 3.
            options.put("theme.color", "#3399cc")
            options.put("currency", "INR")
            options.put("amount", "1000") //pass amount in currency subunits
            options.put("prefill.email", "aman.kumar.choudhary@yopmail.com")
            options.put("prefill.contact", "1234567898")
            checkout.open(activity, options)
        } catch (e: Exception) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e)
        }
    }

    /* override fun onPaymentError(p0: Int, p1: String?) {
         Log.d("yesyes n", p1)
         Toast.makeText(this, "Payment failed", Toast.LENGTH_SHORT).show()
     }

     override fun onPaymentSuccess(p0: String?) {
         Log.d("yesyes n", p0)
         Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()
     }*/

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Log.d("yesyes a", p0 + " " + p1.toString())
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
//        TODO("Not yet implemented")
    }
}