package com.example.coffeeshop;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assuming you have a RecyclerView in your layout with id recyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a list of coffee items (you can replace this with your data source)
        List<CoffeeItem> coffeeItems = new ArrayList<>();
        coffeeItems.add(new CoffeeItem("Espresso Rs100", R.drawable.espresso));
        coffeeItems.add(new CoffeeItem("Latte Rs150", R.drawable.latte));
        coffeeItems.add(new CoffeeItem("Mocha Rs100", R.drawable.mocha));
        coffeeItems.add(new CoffeeItem("Flat White Rs130", R.drawable.flatwhite));
        coffeeItems.add(new CoffeeItem("Cold Brew Rs120", R.drawable.coldbrew));
        // Add more items as needed

        // Create a CoffeeAdapter with the click listeners
        CoffeeAdapter coffeeAdapter = new CoffeeAdapter(coffeeItems);
        coffeeAdapter.setClickListener(new CoffeeAdapter.ClickListener() {
            @Override
            public void onOrderClick(int position) {
                // Handle order click
                Toast.makeText(MainActivity.this, "Ordered successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAddToCartClick(int position) {
                // Handle add to cart click
                Toast.makeText(MainActivity.this, "Added to cart successfully", Toast.LENGTH_SHORT).show();
            }
        });

        // Set the item click listener
        coffeeAdapter.setOnItemClickListener(position -> {
            // Handle item click
            Toast.makeText(MainActivity.this, "Item clicked at position " + position, Toast.LENGTH_SHORT).show();
        });

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(coffeeAdapter);
    }
}
