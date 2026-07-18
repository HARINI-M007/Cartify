package com.example.cartify;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        loadFragment(new HomeFragment());

        bottomNavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_home) {

                loadFragment(new HomeFragment());

            } else if (item.getItemId() == R.id.nav_category) {

                loadFragment(new CategoryFragment());

            } else if (item.getItemId() == R.id.nav_cart) {

                loadFragment(new CartFragment());

            } else if (item.getItemId() == R.id.nav_orders) {

                loadFragment(new OrdersFragment());

            } else if (item.getItemId() == R.id.nav_profile) {

                loadFragment(new ProfileFragment());

            }

            return true;
        });
    }

    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameContainer, fragment)
                .commit();
    }
}