package com.example.cartify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView rvCategories;
    ArrayList<CategoryModel> categoryList;
    ViewPager2 bannerViewPager;
    ArrayList<BannerModel> bannerList;
    private Handler sliderHandler = new Handler(Looper.getMainLooper());
    private Runnable sliderRunnable;
    RecyclerView rvTopDemand;
    ArrayList<TopDemandModel> demandList;
    RecyclerView recyclerProducts;
    ArrayList<ProductModel> productList;

    EditText etSearch;
    ProductAdapter productAdapter;
    TextView txtNoProducts;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_home,
                container,
                false);

        rvCategories = view.findViewById(R.id.rvCategories);

        categoryList = new ArrayList<>();

        categoryList.add(new CategoryModel("Fashion", R.drawable.fashion));
        categoryList.add(new CategoryModel("Electronics", R.drawable.electronics));
        categoryList.add(new CategoryModel("Beauty", R.drawable.beauty));
        categoryList.add(new CategoryModel("Women", R.drawable.women));
        categoryList.add(new CategoryModel("Footwear", R.drawable.footwear));
        categoryList.add(new CategoryModel("Watch", R.drawable.watch));
        rvTopDemand = view.findViewById(R.id.rvTopDemand);
        txtNoProducts = view.findViewById(R.id.txtNoProducts);

        demandList = new ArrayList<>();

        demandList.add(new TopDemandModel("Electronics", R.drawable.topdemand1));
        demandList.add(new TopDemandModel("Bangles", R.drawable.topdemand2));
        demandList.add(new TopDemandModel("Glasses", R.drawable.topdemand3));
        demandList.add(new TopDemandModel("Jeans", R.drawable.topdemand4));
        demandList.add(new TopDemandModel("T-shirts", R.drawable.topdemand5));

        TopDemandAdapter demandAdapter =
                new TopDemandAdapter(demandList);

        rvTopDemand.setLayoutManager(
                new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));


        rvTopDemand.setAdapter(demandAdapter);
        bannerViewPager =
                view.findViewById(R.id.bannerViewPager);

        bannerList = new ArrayList<>();

        bannerList.add(
                new BannerModel(R.drawable.banner1));

        bannerList.add(
                new BannerModel(R.drawable.banner2));

        bannerList.add(
                new BannerModel(R.drawable.banner3));

        BannerAdapter bannerAdapter =
                new BannerAdapter(bannerList);
        recyclerProducts =
                view.findViewById(R.id.recyclerProducts);

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList);

        recyclerProducts.setLayoutManager(
                new androidx.recyclerview.widget.GridLayoutManager(
                        getContext(),
                        2));

        recyclerProducts.setAdapter(productAdapter);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("products")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    productList.clear();

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {

                        ProductModel product =
                                document.toObject(ProductModel.class);

                        productList.add(product);

                    }

                    productAdapter.notifyDataSetChanged();

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(
                            getContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                });




        productAdapter = new ProductAdapter(productList);

        recyclerProducts.setLayoutManager(
                new androidx.recyclerview.widget.GridLayoutManager(
                        getContext(),
                        2));

        recyclerProducts.setAdapter(productAdapter);
        etSearch = view.findViewById(R.id.etSearch);
        txtNoProducts = view.findViewById(R.id.txtNoProducts);

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s,
                                      int start,
                                      int before,
                                      int count) {

                productAdapter.filter(s.toString());

                if (productAdapter.getItemCount() == 0) {

                    txtNoProducts.setVisibility(View.VISIBLE);
                    recyclerProducts.setVisibility(View.GONE);

                } else {

                    txtNoProducts.setVisibility(View.GONE);
                    recyclerProducts.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        bannerViewPager.setAdapter(
                bannerAdapter);
        sliderRunnable = new Runnable() {
            @Override
            public void run() {

                int currentItem =
                        bannerViewPager.getCurrentItem();

                int totalItems =
                        bannerAdapter.getItemCount();

                if (currentItem == totalItems - 1) {
                    bannerViewPager.setCurrentItem(0);
                } else {
                    bannerViewPager.setCurrentItem(
                            currentItem + 1
                    );
                }

                sliderHandler.postDelayed(this, 3000);
            }

        };

        sliderHandler.postDelayed(
                sliderRunnable,
                3000
        );

        CategoryAdapter adapter =
                new CategoryAdapter(categoryList);

        rvCategories.setLayoutManager(
                new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));

        rvCategories.setAdapter(adapter);

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        sliderHandler.removeCallbacks(
                sliderRunnable
        );
    }
}