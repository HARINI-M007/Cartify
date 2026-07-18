package com.example.cartify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BannerAdapter
        extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    ArrayList<BannerModel> list;

    public BannerAdapter(ArrayList<BannerModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banner,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        holder.imgBanner.setImageResource(
                list.get(position).getImage()
        );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imgBanner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBanner =
                    itemView.findViewById(R.id.imgBanner);
        }
    }
}