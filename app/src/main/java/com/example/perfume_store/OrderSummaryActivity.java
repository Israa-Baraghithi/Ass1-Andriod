package com.example.perfume_store;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderSummaryActivity extends AppCompatActivity {

    EditText inputName, inputAddress;
    Spinner paymentSpinner;
    TextView totalPriceText;
    Button confirmButton, cancelButton;

    ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        inputName = findViewById(R.id.inputName);
        inputAddress = findViewById(R.id.inputAddress);
        paymentSpinner = findViewById(R.id.paymentSpinner);
        totalPriceText = findViewById(R.id.totalPriceText);
        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);
        logoImage = findViewById(R.id.logoImage);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Cash on Delivery", "Credit Card", "PayPal"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentSpinner.setAdapter(adapter);

        double total = Cart.getTotalPrice(this);
        totalPriceText.setText("Total Price: $" + total);

        confirmButton.setOnClickListener(v -> {
            String name = inputName.getText().toString().trim();
            String address = inputAddress.getText().toString().trim();
            String payment = paymentSpinner.getSelectedItem().toString();

            if (name.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Order Confirmed 🎉", Toast.LENGTH_LONG).show();
                Cart.clearCart(this);
                finish();
            }
        });
        cancelButton.setOnClickListener(v -> finish());
    }
}
