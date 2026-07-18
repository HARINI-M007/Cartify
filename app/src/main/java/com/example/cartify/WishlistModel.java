package com.example.cartify;

public class WishlistModel {

    String name, price, discount;
    int image;
    public WishlistModel() {
    }

    public WishlistModel(String name, String price, String discount, int image) {
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

    public int getImage() {
        return image;
    }

}