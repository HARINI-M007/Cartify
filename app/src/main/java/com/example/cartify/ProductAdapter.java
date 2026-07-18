package com.example.cartify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.content.Intent;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<ProductModel> list;
    ArrayList<ProductModel> fullList;

    public ProductAdapter(ArrayList<ProductModel> list) {
        this.list = list;
        this.fullList = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductModel model = list.get(position);

        holder.txtProductName.setText(model.getName());
        holder.txtPrice.setText(model.getPrice());
        holder.txtDiscount.setText(model.getDiscount());
        int imageId = holder.itemView.getContext()
                .getResources()
                .getIdentifier(
                        model.getImage(),
                        "drawable",
                        holder.itemView.getContext().getPackageName());

        holder.imgProduct.setImageResource(imageId);
        holder.txtRating.setText("⭐ 4.5");
        holder.imgWishlist.setOnClickListener(v -> {

            WishlistModel wishlist = new WishlistModel(
                    model.getName(),
                    model.getPrice(),
                    model.getDiscount(),
                    imageId
            );

            // Local Wishlist
            WishlistManager.wishlist.add(wishlist);

            // Firestore Wishlist
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                String uid = FirebaseAuth.getInstance()
                        .getCurrentUser()
                        .getUid();

                db.collection("users")
                        .document(uid)
                        .collection("wishlist")
                        .add(wishlist);
            }



            Toast.makeText(
                    v.getContext(),
                    "Added to Wishlist",
                    Toast.LENGTH_SHORT
            ).show();

        });




        // Product Click
        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(view.getContext(), ProductDetailsActivity.class);

            intent.putExtra("name", model.getName());
            intent.putExtra("price", model.getPrice());
            intent.putExtra("discount", model.getDiscount());
            intent.putExtra("image", imageId);

            view.getContext().startActivity(intent);


        });

        // Add To Cart
        holder.btnAddCart.setOnClickListener(button -> {

            CartManager.cartList.add(
                    new CartModel(
                            model.getName(),
                            model.getPrice(),
                            model.getDiscount(),
                            imageId
                    )
            );


            Toast.makeText(
                    button.getContext(),
                    model.getName() + " Added to Cart",
                    Toast.LENGTH_SHORT
            ).show();

        });

    }
    public void filter(String text) {

        list.clear();

        if (text.isEmpty()) {

            list.addAll(fullList);

        } else {

            text = text.toLowerCase();

            for (ProductModel item : fullList) {

                if (item.getName().toLowerCase().contains(text)) {
                    list.add(item);
                }

            }

        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView txtProductName, txtPrice, txtDiscount, txtRating;
        MaterialButton btnAddCart;
        ImageView imgWishlist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtDiscount = itemView.findViewById(R.id.txtDiscount);
            txtRating = itemView.findViewById(R.id.txtRating);
            btnAddCart = itemView.findViewById(R.id.btnAddCart);
            imgWishlist = itemView.findViewById(R.id.imgWishlist);

        }
    }
    public int getFilteredCount() {
        return list.size();
    }
}