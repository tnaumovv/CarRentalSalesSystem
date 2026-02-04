package com.carrental.entity;

public class Car extends Vehicle {
    private String description;
    private CarCategory category;

    public Car() {}

    public Car(String brand, String model, int year, String description, String location, double price, CarCategory category) {
        super(brand, model, year, location, price);
        this.description = description;
        this.category = category;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public CarCategory getCategory() { return category; }
    public void setCategory(CarCategory category) { this.category = category; }

    @Override
    public String getVehicleType() { return "Car"; }

    @Override
    public String getInfo() {
        String categoryStr = (category != null) ? " | Category: " + category.getDisplayName() : "";
        return String.format("ID: %d | %s | %s | %s%s",
                getId(), getVehicleType(), super.getInfo(), description, categoryStr);
    }

    @Override
    public String toString() {
        return getInfo();
    }
}