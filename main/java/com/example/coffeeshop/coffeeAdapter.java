package com.example.coffeeshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    private final List<CoffeeItem> coffeeItems;
    private OnItemClickListener onItemClickListener;
    private static ClickListener clickListener;

    public CoffeeAdapter(List<CoffeeItem> coffeeItems) {
        this.coffeeItems = coffeeItems;
    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_coffee, parent, false);
        return new CoffeeViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull CoffeeViewHolder holder, int position) {
        CoffeeItem coffeeItem = coffeeItems.get(position);
        holder.textCoffeeName.setText(coffeeItem.getCoffeeName());
        holder.imageCoffee.setImageResource(coffeeItem.getImageResourceId());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });

        // Set a different background or icon based on whether the item is in the cart
        int backgroundColor = coffeeItem.isInCart() ? R.color.inCartColor : R.color.defaultColor;
        holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), backgroundColor));

        holder.buttonOrder.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onOrderClick(position);
                // Display a message here (e.g., using Toast) for ordering successfully
            }
        });

        holder.buttonAddToCart.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onAddToCartClick(position);

                // Assuming you have a reference to the CoffeeItem instance
                CoffeeItem clickedItem = coffeeItems.get(position);

                // Toggle the cart status of the clicked item
                clickedItem.setInCart(!clickedItem.isInCart());

                // Display a message or perform other actions as needed
                Toast.makeText(v.getContext(), clickedItem.isInCart() ? "Added to cart successfully" : "Removed from cart", Toast.LENGTH_SHORT).show();

                // Notify the adapter that the data has changed
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return coffeeItems.size();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setClickListener(ClickListener clickListener) {
        CoffeeAdapter.clickListener = clickListener;
    }

    static class CoffeeViewHolder extends RecyclerView.ViewHolder {
        TextView textCoffeeName;
        ImageView imageCoffee;
        Button buttonOrder;
        Button buttonAddToCart;

        public CoffeeViewHolder(@NonNull View itemView) {
            super(itemView);
            textCoffeeName = itemView.findViewById(R.id.textCoffeeName);
            imageCoffee = itemView.findViewById(R.id.imageCoffee);
            buttonOrder = itemView.findViewById(R.id.buttonOrder);
            buttonAddToCart = itemView.findViewById(R.id.buttonAddToCart);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface ClickListener {
        void onOrderClick(int position);
        void onAddToCartClick(int position);
    }
}

