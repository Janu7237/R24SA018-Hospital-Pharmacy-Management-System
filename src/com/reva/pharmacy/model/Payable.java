package com.reva.pharmacy.model;

public interface Payable {
    double calculateAmount();
    void printReceipt();
}
