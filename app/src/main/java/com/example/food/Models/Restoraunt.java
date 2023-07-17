package com.example.food.Models;

public class Restoraunt {

    private int id;
    private String name;
    private String details;
    private byte[] image;

    public Restoraunt(String name, String details,byte[] image) {
        this.name = name;
        this.details = details;
        this.image=image;
    }
    public Restoraunt(String name, String details){
        this.name = name;
        this.details = details;
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
