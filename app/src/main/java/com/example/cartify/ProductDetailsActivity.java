package com.example.cartify;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailsActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView txtName, txtPrice, txtDiscount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        imgProduct = findViewById(R.id.imgProduct);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDiscount = findViewById(R.id.txtDiscount);

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String discount = getIntent().getStringExtra("discount");
        int image = getIntent().getIntExtra("image", 0);

        txtName.setText(name);
        txtPrice.setText(price);
        txtDiscount.setText(discount);
        imgProduct.setImageResource(image);
    }
}