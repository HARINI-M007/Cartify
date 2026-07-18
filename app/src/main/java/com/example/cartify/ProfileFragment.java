package com.example.cartify;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    TextView txtName, txtEmail;
    MaterialButton btnOrders, btnWishlist, btnLogout;

    FirebaseAuth mAuth;
    MaterialButton btnSettings;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_profile,
                container,
                false);

        txtName = view.findViewById(R.id.txtName);
        txtEmail = view.findViewById(R.id.txtEmail);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnOrders = view.findViewById(R.id.btnOrders);
        btnWishlist = view.findViewById(R.id.btnWishlist);
        btnSettings = view.findViewById(R.id.btnSettings);


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {

            txtEmail.setText(user.getEmail());

            if (user.getDisplayName() != null) {
                txtName.setText(user.getDisplayName());
            } else {
                txtName.setText("Cartify User");
            }
        }
        btnOrders.setOnClickListener(v -> {

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameContainer, new OrdersFragment())
                    .addToBackStack(null)
                    .commit();

        });


        btnWishlist.setOnClickListener(v -> {

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameContainer, new WishlistFragment())
                    .addToBackStack(null)
                    .commit();

        });
        btnSettings.setOnClickListener(v -> {

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameContainer, new SettingsFragment())
                    .addToBackStack(null)
                    .commit();

        });

        btnLogout.setOnClickListener(v -> {

            mAuth.signOut();

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);

        });


        return view;
    }
}