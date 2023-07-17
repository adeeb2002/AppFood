package com.example.food.Models;

public class UserModel {
    private int idUser;
    private String userName;
    private String passowrd;
    private String email;
    private String phone;
    private String country;
    private byte[] imageUser;

    public UserModel(String userName, String passowrd) {
        this.userName = userName;
        this.passowrd = passowrd;
    }

    public UserModel(String userName, String passowrd, String email, String phone, String country,byte[] imageUser) {
        this.userName = userName;
        this.passowrd = passowrd;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.imageUser=imageUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public byte[] getImageUser() {
        return imageUser;
    }

    public void setImageUser(byte[] imageUser) {
        this.imageUser = imageUser;
    }
}
