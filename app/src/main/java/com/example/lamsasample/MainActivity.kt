package com.example.lamsasample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import om.thawani.lamsa.sdk.LamsaSDK
import om.thawani.lamsa.sdk.enums.PaymentOptions
import om.thawani.lamsa.sdk.models.InitOptionsModel
import om.thawani.lamsa.sdk.models.PaymentResultModel


class MainActivity : AppCompatActivity() {

    companion object {
        const val LAMSA_REQUEST_CODE = 200
    }
    private lateinit var myButton:Button
    private lateinit var myTextView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
            myButton = findViewById(R.id.button)
           myTextView = findViewById(R.id.textView)

            myButton.setOnClickListener {
                val intent = Intent(this, LamsaSDK::class.java)
                val args = InitOptionsModel(
                    amount = 0.105,
                    authKey = "wjOcN7XEG7q6vlSWO15QRRRjn3JI8mimxGwANT38",
                    remarks = "Thawani SDK for external NFC",
                    isProduction = false,
                    paymentOption = PaymentOptions.CARD_ACCEPT,
//                    autoCloseInMillis = 3000 //Optional, auto close after 3 second of transaction finished processed
                )
                intent.putExtra("SDKInitOptions", args)
                this.startActivityForResult(intent, LAMSA_REQUEST_CODE)

            }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        runOnUiThread {
            if (requestCode == LAMSA_REQUEST_CODE) {
                // If payment success
                if (resultCode == Activity.RESULT_OK) {
                    val result = data?.getSerializableExtra("result") as? PaymentResultModel

                    if (result != null) {
                        //You can Convert JsonString to what you required
//                    binding.result.text = result.toString()
                        val isPaymentSuccess: Boolean = result.success ?: false
                        myTextView.text = "Payment Success: $isPaymentSuccess"

                    } else {
                        // Handle unexpected error
                        myTextView.text = "unexpected error occurred"
                    }
                }

                // If payment cancelled or failed
                if (resultCode == Activity.RESULT_CANCELED) {
                    val result =
                        data?.getSerializableExtra("result") as? PaymentResultModel
                    if (result != null) {
                        //You can Convert JsonString to what you required
//                    binding.result.text = result.toString()
                        val isPaymentSuccess: Boolean = result.success ?: false
                        myTextView.text = "Payment Success:$isPaymentSuccess"
                    } else {
                        // Handle unexpected error
                        myTextView.text = "unexpected error occurred"
                    }
                }
            }
        }
    }


}