package com.example.hellofx;

public class Customer {
    private String name;
    private String province;

    // Constructor
    public Customer(String name, String province) {
        this.name = name;
        this.province = province;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }
}