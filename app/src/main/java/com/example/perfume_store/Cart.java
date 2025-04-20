package com.example.perfume_store;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static final String TOTAL_PRICE = "TOTAL_PRICE";

    public static void addToCart(Context context, Perfume perfume) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        int size = prefs.getInt("cart_size", 0);

        editor.putString("item_name_" + size, perfume.getName());
        editor.putFloat("item_price_" + size, perfume.getPrice());
        editor.putInt("item_image_" + size, perfume.getImageResource());

        editor.putInt("cart_size", size + 1);

        float total = prefs.getFloat(TOTAL_PRICE, 0);
        total += perfume.getPrice();
        editor.putFloat(TOTAL_PRICE, total);

        editor.apply();
    }

    public static List<Perfume> getCartItems(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        List<Perfume> items = new ArrayList<>();

        int size = prefs.getInt("cart_size", 0);

        for (int i = 0; i < size; i++) {
            String name = prefs.getString("item_name_" + i, "");
            float price = prefs.getFloat("item_price_" + i, 0);
            int image = prefs.getInt("item_image_" + i, 0);
            items.add(new Perfume(name, price, image));
        }

        return items;
    }

    public static double getTotalPrice(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getFloat(TOTAL_PRICE, 0);
    }

    public static void clearCart(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        int size = prefs.getInt("cart_size", 0);

        for (int i = 0; i < size; i++) {
            editor.remove("item_name_" + i);
            editor.remove("item_price_" + i);
            editor.remove("item_image_" + i);
        }

        editor.remove("cart_size");
        editor.remove(TOTAL_PRICE);

        editor.apply();
    }
}
