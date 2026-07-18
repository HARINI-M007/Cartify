package com.example.cartify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    RecyclerView rvCategory;
    ArrayList<CategoryModel> categoryList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_category,
                container,
                false);

        rvCategory = view.findViewById(R.id.rvCategory);

        categoryList = new ArrayList<>();

        categoryList.add(new CategoryModel("Fashion", R.drawable.fashion));
        categoryList.add(new CategoryModel("Electronics", R.drawable.electronics));
        categoryList.add(new CategoryModel("Beauty", R.drawable.beauty));
        categoryList.add(new CategoryModel("Women", R.drawable.women));
        categoryList.add(new CategoryModel("Footwear", R.drawable.footwear));
        categoryList.add(new CategoryModel("Watch", R.drawable.watch));

        CategoryAdapter adapter = new CategoryAdapter(categoryList);

        rvCategory.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvCategory.setAdapter(adapter);

        return view;
    }
}