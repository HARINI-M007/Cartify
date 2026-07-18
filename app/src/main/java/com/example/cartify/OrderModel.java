package com.example.cartify;

public class OrderModel {

    String name, price;
    int image;

    // Required for Firestore
    public OrderModel() {
    }

    public OrderModel(String name, String price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}