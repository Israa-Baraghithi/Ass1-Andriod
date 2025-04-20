package com.example.perfume_store;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private TextView totalPriceText;
    private Button clearCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        cartListView = findViewById(R.id.cartListView);
        totalPriceText = findViewById(R.id.totalPriceText);
        clearCartButton = findViewById(R.id.clearCartButton);

        Button confirmOrderButton = findViewById(R.id.confirmOrderButton);



        confirmOrderButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, OrderSummaryActivity.class);
            startActivity(intent);
        });


        List<Perfume> cartItems = Cart.getCartItems(this);
        PerfumeAdapter adapter = new PerfumeAdapter(this, cartItems);
        cartListView.setAdapter(adapter);

        double total = Cart.getTotalPrice(this);
        totalPriceText.setText(" Total price: $" + total);

        clearCartButton.setOnClickListener(v -> {
            Cart.clearCart(this);
            cartListView.setAdapter(new PerfumeAdapter(this, Cart.getCartItems(this)));
            totalPriceText.setText(" Total price: $0.00");
            Toast.makeText(this, "Deleted Successfuly 🧹", Toast.LENGTH_SHORT).show();
        });
    }




}
