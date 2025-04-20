package com.example.perfume_store;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnBrowse, btnCart, btnAbout, btnsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBrowse = findViewById(R.id.btnBrowse);
        btnCart = findViewById(R.id.btnCart);
        btnAbout = findViewById(R.id.btnAbout);
        btnsearch = findViewById(R.id.btnSearch);

        btnBrowse.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BrowseActivity.class);
            startActivity(intent);
        });

        btnsearch.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });

        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }
}
