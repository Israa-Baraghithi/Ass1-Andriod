package com.example.perfume_store;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    Spinner spinnerType;
    EditText edtName, edtPrice;
    RadioGroup radioGroup;
    CheckBox checkVanilla, checkRose, checkMusk, checklavender;
    Button btnSearch;
    ListView listViewResults;

    ArrayList<Perfume> perfumeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        spinnerType = findViewById(R.id.spinnerType);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        radioGroup = findViewById(R.id.radioGroup);
        checkVanilla = findViewById(R.id.checkVanilla);
        checkRose = findViewById(R.id.checkRose);
        checkMusk = findViewById(R.id.checkMusk);
        checklavender = findViewById(R.id.checklavender);
        btnSearch = findViewById(R.id.btnSearch);
        listViewResults = findViewById(R.id.listViewResults);

        loadPerfumesFromPrefs();

        btnSearch.setOnClickListener(v -> {
            applyFilters();
        });

    }

    private void loadPerfumesFromPrefs() {
        SharedPreferences prefs = getSharedPreferences("PerfumePrefs", MODE_PRIVATE);

        for (int i = 0; i < 14; i++) {
            String name = prefs.getString("perfumeName" + i, "");
            if (!name.isEmpty()) {
                float price = prefs.getFloat("perfumePrice" + i, 0);
                int image = prefs.getInt("perfumeImage" + i, 0);
                perfumeList.add(new Perfume(name, price, image));
            }
        }

        Toast.makeText(this, "Loaded: " + perfumeList.size(), Toast.LENGTH_SHORT).show();
    }

    private void applyFilters() {
        ArrayList<Perfume> filteredList = new ArrayList<>();

        String nameFilter = edtName.getText().toString().trim().toLowerCase();
        String priceStr = edtPrice.getText().toString().trim();
        double maxPrice = priceStr.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(priceStr);

        int selectedPriceTypeId = radioGroup.getCheckedRadioButtonId();
        boolean isLowPrice = selectedPriceTypeId == R.id.radioLow;
        boolean isPriceFilterSelected = selectedPriceTypeId == R.id.radioLow || selectedPriceTypeId == R.id.radioHigh;

        boolean filterVanilla = checkVanilla.isChecked();
        boolean filterRose = checkRose.isChecked();
        boolean filterMusk = checkMusk.isChecked();
        boolean filterLavender = checklavender.isChecked();

        String selectedType = spinnerType.getSelectedItem().toString().toLowerCase();
        boolean filterByType = !selectedType.equals("all");

        for (Perfume perfume : perfumeList) {
            String nameLower = perfume.getName().toLowerCase();
            double price = perfume.getPrice();

            boolean matchesName = nameFilter.isEmpty() || nameLower.contains(nameFilter);
            boolean matchesPrice = priceStr.isEmpty() || price <= maxPrice;
            boolean matchesLowHigh = !isPriceFilterSelected ||
                    (isLowPrice && price <= 500) ||
                    (!isLowPrice && price > 500);
            boolean matchesVanilla = !filterVanilla || nameLower.contains("vanilla");
            boolean matchesRose = !filterRose || nameLower.contains("rose");
            boolean matchesMusk = !filterMusk || nameLower.contains("musk");
            boolean matchesLavender = !filterLavender || nameLower.contains("lavender");
            boolean matchesType = !filterByType || nameLower.contains(selectedType);

            if (matchesName && matchesPrice && matchesLowHigh &&
                    matchesVanilla && matchesRose && matchesMusk &&
                    matchesLavender && matchesType) {
                filteredList.add(perfume);
            }
        }


        PerfumeAdapter adapter = new PerfumeAdapter(this, filteredList);
        listViewResults.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        Toast.makeText(this, "Item foune " + filteredList.size() + " perfume 🪻", Toast.LENGTH_SHORT).show();
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No item found 😔", Toast.LENGTH_SHORT).show();
        }

    }

}

