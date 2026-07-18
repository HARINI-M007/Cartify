package com.example.cartify;

public class TopDemandModel {

    private String name;
    private int image;

    public TopDemandModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}