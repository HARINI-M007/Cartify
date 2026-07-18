package com.example.cartify;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class OrderSuccessActivity extends AppCompatActivity {

    MaterialButton btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(v -> {

            CartManager.cartList.clear();

            Intent intent = new Intent(
                    OrderSuccessActivity.this,
                    MainActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();

        });
    }
}