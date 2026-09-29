package com.reva.pharmacy.model;

import java.util.Objects;

public class Medicine {
    private static int totalMedicinesCreated = 0;
    private final int medicineId;
    private String name;
    private MedicineCategory category;
    private double price;
    private int stock;
    private String expiryDate;

    public Medicine() {
        this(0, "Unnamed Medicine", MedicineCategory.OTHER, 0.0, 0, "N/A");
    }

    public Medicine(int medicineId, String name, MedicineCategory category,
                    double price, int stock, String expiryDate) {
        this.medicineId = medicineId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.expiryDate = expiryDate;
        totalMedicinesCreated++;
    }

    public int getMedicineId() { return medicineId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public MedicineCategory getCategory() { return category; }
    public void setCategory(MedicineCategory category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public static int getTotalMedicinesCreated() { return totalMedicinesCreated; }

    public void reduceStock(int quantity) {
        if (quantity > 0 && quantity <= stock) {
            stock -= quantity;
        }
    }

    @Override
    public String toString() {
        return String.format("%-4d %-18s %-12s Rs.%8.2f %5d %-12s",
                medicineId, name, category, price, stock, expiryDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Medicine)) return false;
        Medicine other = (Medicine) obj;
        return medicineId == other.medicineId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicineId);
    }
}
