package com.example.perfume_store;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity {

    ListView listViewPerfumes;
    TextView qoute;
    ArrayList<Perfume> perfumeList;
    ArrayList<Perfume> originalList;
    PerfumeAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);

        listViewPerfumes = findViewById(R.id.listViewPerfumes);
        qoute = findViewById(R.id.qoute);
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("PerfumePrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Initialize the perfume list
        perfumeList = new ArrayList<>();

        // Load perfumes from SharedPreferences
        loadPerfumes();

        originalList = new ArrayList<>(perfumeList); // backup list for filtering

        // Use custom adapter
        adapter = new PerfumeAdapter(this, perfumeList);
        listViewPerfumes.setAdapter(adapter);

        // Display Toast to check number of loaded items
        Toast.makeText(this, "Items loaded: " + perfumeList.size(), Toast.LENGTH_SHORT).show();
    }

    // Method to load the perfumes list from SharedPreferences
    private void loadPerfumes() {
        // Load each perfume one by one from SharedPreferences
        for (int i = 0; i < 14; i++) {
            String name = sharedPreferences.getString("perfumeName" + i, "");
            if (!name.isEmpty()) {
                float price = sharedPreferences.getFloat("perfumePrice" + i, 0);
                int image = sharedPreferences.getInt("perfumeImage" + i, 0);
                perfumeList.add(new Perfume(name, price, image));
            }
        }

        if (perfumeList.isEmpty()) {
            loadDefaultPerfumes();
            savePerfumes(); // Save the default perfumes to SharedPreferences
        }
    }

    // Method to load the default perfumes list
    private void loadDefaultPerfumes() {
        perfumeList.add(new Perfume("Vanilla Perfume", 250.0f, R.drawable.photo1));
        perfumeList.add(new Perfume("Rose Lavender ", 450.0f, R.drawable.photo2));
        perfumeList.add(new Perfume("Lady Dior", 550.0f, R.drawable.photo3));
        perfumeList.add(new Perfume("Rose Essence", 600.0f, R.drawable.photo4));
        perfumeList.add(new Perfume("Dior Breeze", 400.0f, R.drawable.photo5));
        perfumeList.add(new Perfume("Ocean Mist", 600.0f, R.drawable.photo6));
        perfumeList.add(new Perfume("Rose Blossom", 550.0f, R.drawable.photo7));
        perfumeList.add(new Perfume("Fresh Lavender", 500.0f, R.drawable.photo8));
        perfumeList.add(new Perfume("Amber Night", 650.0f, R.drawable.photo9));
        perfumeList.add(new Perfume("Musk Essence", 700.0f, R.drawable.photo10));
        perfumeList.add(new Perfume("Golden Jasmine", 800.0f, R.drawable.photo11));
        perfumeList.add(new Perfume("Musk Delight", 450.0f, R.drawable.photo12));
        perfumeList.add(new Perfume("Mystic Orchid", 750.0f, R.drawable.photo13));
        perfumeList.add(new Perfume("Sandalwood Dream", 850.0f, R.drawable.photo14));
    }

    // Method to save the perfumes list to SharedPreferences
    private void savePerfumes() {
        // Save each perfume to SharedPreferences individually
        for (int i = 0; i < perfumeList.size(); i++) {
            Perfume perfume = perfumeList.get(i);
            editor.putString("perfumeName" + i, perfume.getName());
            editor.putFloat("perfumePrice" + i, perfume.getPrice());
            editor.putInt("perfumeImage" + i, perfume.getImageResource());
        }
        editor.apply(); // Save asynchronously
    }
}
