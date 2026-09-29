package com.reva.pharmacy.model;

public class Customer extends Person {
    private String address;

    public Customer() {
        this(0, "Guest Customer", "Not provided", "Not provided");
    }

    public Customer(int id, String name, String phone, String address) {
        super(id, name, phone);
        this.address = address;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String getRole() {
        return "Customer";
    }

    @Override
    public String toString() {
        return String.format("Customer{id=%d, name='%s', phone='%s', address='%s'}",
                getId(), getName(), getPhone(), address);
    }
}
