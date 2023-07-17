package com.example.food.Models;

public class FruitsModel {
    private int id;
    private String name;
    private double price;
    private double totalPrice;
    private String details;
    private boolean isLike;
    private int qoantity;
    private byte[] image;

    public FruitsModel( String name, double price,double totalPrice, String details, byte[] image) {
        this.name = name;
        this.price = price;
        this.totalPrice=totalPrice;
        this.details = details;
        this.image = image;
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

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
    public int getQoantity() {
        return qoantity;
    }

    public void setQoantity(int qoantity) {
        this.qoantity = qoantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
