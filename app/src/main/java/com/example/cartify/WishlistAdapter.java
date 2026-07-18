package com.example.cartify;

import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    ArrayList<WishlistModel> list;

    public WishlistAdapter(ArrayList<WishlistModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wishlist,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        WishlistModel model = list.get(position);

        holder.img.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.discount.setText(model.getDiscount());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,price,discount;

        ViewHolder(View itemView){

            super(itemView);

            img=itemView.findViewById(R.id.imgWishlistProduct);
            name=itemView.findViewById(R.id.txtWishlistName);
            price=itemView.findViewById(R.id.txtWishlistPrice);
            discount=itemView.findViewById(R.id.txtWishlistDiscount);

        }

    }
}