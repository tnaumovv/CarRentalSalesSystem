package com.carrental.entity;

import java.time.LocalDate;

public class Rental {
    private int id;
    private int carId;
    private int duration;
    private double totalPrice;
    private LocalDate startDate;
    private LocalDate endDate;

    public Rental() {}

    public Rental(int carId, int duration, double totalPrice, LocalDate startDate, LocalDate endDate) {
        this.carId = carId;
        this.duration = duration;
        this.totalPrice = totalPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}