package com.example.cartify;

public class CartModel {

    private String name;
    private String price;
    private String discount;
    private int image;
    private int quantity;

    public CartModel(String name, String price, String discount, int image) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.quantity = 1;
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

    public int getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}