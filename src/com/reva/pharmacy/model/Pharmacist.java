package com.reva.pharmacy.model;

public class Pharmacist extends Person {
    private String licenseNumber;

    public Pharmacist() {
        this(0, "Unknown Pharmacist", "Not provided", "Not assigned");
    }

    public Pharmacist(int id, String name, String phone, String licenseNumber) {
        super(id, name, phone);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    @Override
    public String getRole() {
        return "Pharmacist";
    }

    @Override
    public void displaySummary() {
        super.displaySummary();
        System.out.println("License: " + licenseNumber);
    }
}
