package com.taponphone.sdktest

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var buttonPay: Button
    private lateinit var editTextId: EditText
    private lateinit var editTextAmount: EditText
    private lateinit var editTextOrderTracking: EditText
    private lateinit var textViewResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPay = findViewById(R.id.buttonPay)
        editTextId = findViewById(R.id.editTextId)
        editTextAmount = findViewById(R.id.editTextAmount)
        editTextOrderTracking = findViewById(R.id.editTextOrderTracking)
        textViewResponse = findViewById(R.id.textViewResponse)

        buttonPay.setOnClickListener {
            startPayment()
        }
    }

    private fun startPayment() {

        if (editTextAmount.text.toString().isEmpty() || editTextAmount.text.toString().toDouble() < 1) {
            editTextAmount.requestFocus()
            editTextAmount.error = "Amount should be greater than 0"
            return
        }

        val intent = Intent("com.payable.action.TAP_PAYMENT")
        intent.putExtra("ID", editTextId.text.toString())
        intent.putExtra("METHOD", "CARD")
        intent.putExtra("ORDER_TRACKING", editTextOrderTracking.text.toString())
        intent.putExtra("AMOUNT", editTextAmount.text.toString().toDouble())

        try {
            startActivityForResult(intent, 200)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        var response = "requestCode: $requestCode \n"
        response += "ID: ${data?.getStringExtra("ID")} \n"
        response += "METHOD: ${data?.getStringExtra("METHOD")} \n"
        response += "AMOUNT: ${data?.getDoubleExtra("AMOUNT", 0.00)} \n"
        response += "ORDER_TRACKING: ${data?.getStringExtra("ORDER_TRACKING")} \n"
        response += "STATUS: ${data?.getStringExtra("STATUS")} \n"

        textViewResponse.text = response
    }
}