package com.example.food.Models;

public class LikeModel {

    private int id;
    private String name;
    private double price;
    private String details;
    private byte[] image;

    public LikeModel(String name, double price, String details,byte[] image) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

