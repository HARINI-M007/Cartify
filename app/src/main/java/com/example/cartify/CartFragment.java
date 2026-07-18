package com.example.cartify;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

public class CartFragment extends Fragment {

    RecyclerView rvCart;

    MaterialButton btnCheckout;
    TextView txtTotal, txtEmptyCart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_cart,
                container,
                false);

        rvCart = view.findViewById(R.id.rvCart);
        txtTotal = view.findViewById(R.id.txtTotal);
        btnCheckout = view.findViewById(R.id.btnCheckout);
        txtEmptyCart = view.findViewById(R.id.txtEmptyCart);

        CartAdapter adapter = new CartAdapter(
                CartManager.cartList,
                this::calculateTotal
        );

        rvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCart.setAdapter(adapter);

        calculateTotal();

        // Checkout Button
        btnCheckout.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(), CheckoutActivity.class);

            String total = txtTotal.getText().toString()
                    .replace("Total : ₹", "");

            intent.putExtra("total", total);

            startActivity(intent);

        });

        return view;
    }

    private void calculateTotal() {

        int total = 0;

        for (CartModel item : CartManager.cartList) {

            String price = item.getPrice()
                    .replace("₹", "")
                    .replace(",", "");

            total += Integer.parseInt(price) * item.getQuantity();
        }

        txtTotal.setText("Total : ₹" + total);
        if (CartManager.cartList.isEmpty()) {

            txtEmptyCart.setVisibility(View.VISIBLE);
            rvCart.setVisibility(View.GONE);
            btnCheckout.setVisibility(View.GONE);

        } else {

            txtEmptyCart.setVisibility(View.GONE);
            rvCart.setVisibility(View.VISIBLE);
            btnCheckout.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (rvCart != null) {
            rvCart.setAdapter(
                    new CartAdapter(
                            CartManager.cartList,
                            this::calculateTotal
                    )
            );
            calculateTotal();
        }
    }
}