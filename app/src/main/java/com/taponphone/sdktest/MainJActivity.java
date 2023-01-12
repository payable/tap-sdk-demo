package com.taponphone.sdktest;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("deprecation")
public class MainJActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void startPayment() {

        Intent intent = new Intent("com.payable.action.TAP_PAYMENT");
        intent.putExtra("ID", "INV-1001");
        intent.putExtra("METHOD", "CARD");
        intent.putExtra("ORDER_TRACKING", "Customer-1001");
        intent.putExtra("AMOUNT", 100.00);

        try {
            startActivityForResult(intent, 9000);
        } catch (ActivityNotFoundException ex) {
            // PAYable Tap is not installed or outdated
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 9000) {

            String id = data.getStringExtra("ID");
            String method = data.getStringExtra("METHOD");
            String orderTracking = data.getStringExtra("ORDER_TRACKING");
            double amount = data.getDoubleExtra("AMOUNT", 0.00);
            String status =data.getStringExtra("STATUS");
        }
    }
}
