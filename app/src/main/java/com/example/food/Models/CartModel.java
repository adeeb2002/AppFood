package com.example.food.Models;

public class CartModel {

    private int numberCart;
    private int id;
    private String name;
    private double price;
    private double totalPrice;
    private String details;
    private int qoantity;
    private byte[] image;
    private int UserId;

    public CartModel(String name, double price,double TotalPrice, int qoantity, String details,byte[] image) {
        numberCart=id;
        this.name = name;
        this.price = price;
        this.totalPrice=TotalPrice;
        this.qoantity=qoantity;
        this.details = details;
        this.image=image;
    }

    public int getUserId() {
        return UserId;
    }

    public int getNumberCart() {
        return numberCart;
    }

    public void setNumberCart(int numberCart) {
        this.numberCart = numberCart;
    }

    public void setUserId(int userId) {
        UserId = userId;
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
