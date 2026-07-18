package com.example.cartify;

public class ProductModel {

    private String name;
    private String price;
    private String discount;
    private String image;

    public ProductModel() {
        // Required for Firestore
    }

    public ProductModel(String name, String price, String discount, String image) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }

    public String getImage() {
        return image;
    }
}