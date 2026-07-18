package com.example.cartify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<CartModel> list;
    CartUpdateListener listener;

    public CartAdapter(ArrayList<CartModel> list,
                       CartUpdateListener listener) {

        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CartModel model = list.get(position);

        holder.imgCartProduct.setImageResource(model.getImage());
        holder.txtCartName.setText(model.getName());
        holder.txtCartPrice.setText(model.getPrice());
        holder.txtCartDiscount.setText(model.getDiscount());

        holder.txtQuantity.setText(String.valueOf(model.getQuantity()));

        holder.btnPlus.setOnClickListener(v -> {

            int qty = model.getQuantity();
            qty++;

            model.setQuantity(qty);

            holder.txtQuantity.setText(String.valueOf(qty));

            notifyItemChanged(holder.getAdapterPosition());
            listener.onCartUpdated();

        });

        holder.btnMinus.setOnClickListener(v -> {

            int qty = model.getQuantity();

            if (qty > 1) {

                qty--;

                model.setQuantity(qty);

                holder.txtQuantity.setText(String.valueOf(qty));

                notifyItemChanged(holder.getAdapterPosition());
                listener.onCartUpdated();

            }

        });

        holder.txtRemove.setOnClickListener(v -> {

            int adapterPosition = holder.getAdapterPosition();

            if (adapterPosition != RecyclerView.NO_POSITION) {
                list.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                notifyItemRangeChanged(adapterPosition, list.size());

                listener.onCartUpdated();
            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCartProduct;
        TextView txtCartName, txtCartPrice, txtCartDiscount, txtQuantity, txtRemove;
        Button btnPlus, btnMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCartProduct = itemView.findViewById(R.id.imgCartProduct);
            txtCartName = itemView.findViewById(R.id.txtCartName);
            txtCartPrice = itemView.findViewById(R.id.txtCartPrice);
            txtCartDiscount = itemView.findViewById(R.id.txtCartDiscount);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtRemove = itemView.findViewById(R.id.txtRemove);

            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
        }
    }
}