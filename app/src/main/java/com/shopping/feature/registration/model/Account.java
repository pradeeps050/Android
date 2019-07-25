package com.shopping.feature.registration.model;

public class Account {

    private String email;
    private int phone;
    private String password;

    public Account(String email, int phone, String password) {
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
