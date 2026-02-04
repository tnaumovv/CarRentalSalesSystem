package com.carrental.entity;

public enum CarCategory {
    SEDAN("Sedan"),
    SUV("SUV"),
    TRUCK("Truck"),
    SPORTS("Sports Car"),
    VAN("Van"),
    COMPACT("Compact");

    private String displayName;

    CarCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}