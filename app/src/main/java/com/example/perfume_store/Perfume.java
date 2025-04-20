package com.example.perfume_store;

public class Perfume {

    private String name;
    private float price;
    private int imageResource;

    public Perfume(String name, float price, int imageResource) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }
}

