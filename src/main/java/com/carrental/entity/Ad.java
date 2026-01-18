package com.carrental.entity;

public class Ad {
    private int id;
    private int carId;
    private String type;
    private double price;
    private boolean available;

    public Ad() {}

    public Ad(int carId, String type, double price, boolean available) {
        this.carId = carId;
        this.type = type;
        this.price = price;
        this.available = available;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}