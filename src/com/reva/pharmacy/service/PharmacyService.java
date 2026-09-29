package com.reva.pharmacy.service;

import com.reva.pharmacy.model.Medicine;
import com.reva.pharmacy.model.MedicineCategory;
import com.reva.pharmacy.model.Pharmacist;

public class PharmacyService {
    private final Medicine[] medicines;
    private final Pharmacist pharmacist;
    private int medicineCount;

    public PharmacyService(Pharmacist pharmacist, int capacity) {
        this.pharmacist = pharmacist;
        this.medicines = new Medicine[capacity];
        this.medicineCount = 0;
    }

    public void addMedicine(Medicine medicine) {
        if (medicineCount < medicines.length) {
            medicines[medicineCount++] = medicine;
        } else {
            System.out.println("Medicine storage is full.");
        }
    }

    public Medicine[] getMedicines() {
        return medicines;
    }

    public void displayMedicines() {
        System.out.println("\nID   Name               Category     Price      Stock Expiry");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < medicineCount; i++) {
            if (medicines[i] != null) {
                System.out.println(medicines[i]);
            }
        }
    }

    // Method overloading: search by name.
    public Medicine searchMedicine(String name) {
        for (int i = 0; i < medicineCount; i++) {
            if (medicines[i].getName().trim().equalsIgnoreCase(name.trim())) {
                return medicines[i];
            }
        }
        return null;
    }

    // Method overloading: search by name and category.
    public Medicine searchMedicine(String name, MedicineCategory category) {
        for (int i = 0; i < medicineCount; i++) {
            if (medicines[i].getName().toLowerCase().contains(name.trim().toLowerCase())
                    && medicines[i].getCategory() == category) {
                return medicines[i];
            }
        }
        return null;
    }

    public void lowStockReport(int threshold) {
        System.out.println("\n--- Low Stock Report (<= " + threshold + ") ---");
        for (int i = 0; i < medicineCount; i++) {
            if (medicines[i].getStock() <= threshold) {
                System.out.println(medicines[i]);
            } else {
                continue;
            }
        }
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }
}
