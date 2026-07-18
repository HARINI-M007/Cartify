package com.example.cartify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class CheckoutActivity extends AppCompatActivity {

    EditText etName, etPhone, etAddress;
    TextView txtSubtotal, txtDelivery, txtTotal;
    MaterialButton btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);

        txtSubtotal = findViewById(R.id.txtSubtotal);
        txtDelivery = findViewById(R.id.txtDelivery);
        txtTotal = findViewById(R.id.txtTotal);

        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        String subtotal = getIntent().getStringExtra("total");

        if (subtotal == null) {
            subtotal = "0";
        }

        int totalAmount = Integer.parseInt(subtotal);
        int delivery = 40;
        int grandTotal = totalAmount + delivery;

        txtSubtotal.setText("Subtotal : ₹" + totalAmount);
        txtDelivery.setText("Delivery : ₹40");
        txtTotal.setText("Total : ₹" + grandTotal);

        btnPlaceOrder.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            if (name.isEmpty()) {
                etName.setError("Enter your name");
                return;
            }

            if (phone.isEmpty()) {
                etPhone.setError("Enter phone number");
                return;
            }

            if (phone.length() != 10) {
                etPhone.setError("Enter a valid 10-digit phone number");
                return;
            }

            if (address.isEmpty()) {
                etAddress.setError("Enter delivery address");
                return;
            }

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                String uid = FirebaseAuth.getInstance()
                        .getCurrentUser()
                        .getUid();

                for (CartModel item : CartManager.cartList) {

                    OrderModel order = new OrderModel(
                            item.getName(),
                            item.getPrice(),
                            item.getImage()
                    );

                    OrderManager.orderList.add(order);

                    db.collection("users")
                            .document(uid)
                            .collection("orders")
                            .add(order);
                }
            }

            Intent intent = new Intent(
                    CheckoutActivity.this,
                    OrderSuccessActivity.class
            );

            startActivity(intent);
            finish();

        });

    }
}