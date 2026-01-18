package com.carrental.entity;

public class Car extends Vehicle{
    private String description;

    public Car() {}


    public Car(String brand, String model, int year, String description, String location, double price) {
        super(brand, model, year, location, price);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    @Override
    public String getInfo() {
        return String.format("ID: %d | %s | %s | %s",
                getId(), getVehicleType(), super.getInfo(), description);
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
