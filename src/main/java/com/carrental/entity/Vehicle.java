package com.carrental.entity;

public abstract class Vehicle {
    private int id;
    private String brand;
    private String model;
    private int year;
    private String location;
    private double price;

    public Vehicle() {}

    public Vehicle(String brand, String model, int year, String location, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.location = location;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public abstract String getVehicleType();

    public String getInfo() {
        return String.format("%s %s (%d) | Location: %s | Price: $%.2f ",
                brand, model, year, location, price);
    }
}