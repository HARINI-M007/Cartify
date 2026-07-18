package com.example.cartify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopDemandAdapter extends RecyclerView.Adapter<TopDemandAdapter.ViewHolder> {

    ArrayList<TopDemandModel> list;

    public TopDemandAdapter(ArrayList<TopDemandModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top_demand, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TopDemandModel model = list.get(position);

        holder.txtDemand.setText(model.getName());
        holder.imgDemand.setImageResource(model.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDemand;
        TextView txtDemand;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDemand = itemView.findViewById(R.id.imgDemand);
            txtDemand = itemView.findViewById(R.id.txtDemand);
        }
    }
}