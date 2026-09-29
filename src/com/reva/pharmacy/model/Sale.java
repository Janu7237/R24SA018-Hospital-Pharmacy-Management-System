package com.reva.pharmacy.model;

import java.util.ArrayList;
import java.util.List;

public class Sale implements Payable {
    private static int nextBillNumber = 1001;
    private final int billNumber;
    private Customer customer;
    private final List<Medicine> medicines = new ArrayList<>();
    private final List<Integer> quantities = new ArrayList<>();
    private PaymentMethod paymentMethod;
    private boolean member;

    public Sale(Customer customer) {
        this.billNumber = nextBillNumber++;
        this.customer = customer;
        this.paymentMethod = PaymentMethod.CASH;
    }

    public int getBillNumber() { return billNumber; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public boolean isMember() { return member; }
    public void setMember(boolean member) { this.member = member; }

    public void addItem(Medicine medicine, int quantity) {
        if (medicine != null && quantity > 0 && quantity <= medicine.getStock()) {
            medicines.add(medicine);
            quantities.add(quantity);
            medicine.reduceStock(quantity);
        }
    }

    // Method overloading: add one item using quantity 1.
    public void addItem(Medicine medicine) {
        addItem(medicine, 1);
    }

    public List<Medicine> getMedicines() { return medicines; }
    public List<Integer> getQuantities() { return quantities; }

    @Override
    public double calculateAmount() {
        double subtotal = 0;
        for (int i = 0; i < medicines.size(); i++) {
            subtotal += medicines.get(i).getPrice() * quantities.get(i);
        }
        double discount = member ? subtotal * PharmacyConstants.MEMBER_DISCOUNT : 0;
        double taxable = subtotal - discount;
        double gst = taxable * PharmacyConstants.GST_RATE;
        return taxable + gst;
    }

    public double calculateAmount(boolean roundToInteger) {
        double amount = calculateAmount();
        return roundToInteger ? (int) amount : amount; // explicit double-to-int cast
    }

    @Override
    public void printReceipt() {
        System.out.println("\n========== PHARMACY BILL ==========");
        System.out.println("Bill No : " + billNumber);
        System.out.println("Customer: " + customer.getName());
        System.out.println("-----------------------------------");
        for (int i = 0; i < medicines.size(); i++) {
            double line = medicines.get(i).getPrice() * quantities.get(i);
            System.out.printf("%-18s x%-3d Rs.%8.2f%n", medicines.get(i).getName(), quantities.get(i), line);
        }
        double subtotal = 0;
        for (int i = 0; i < medicines.size(); i++) {
            subtotal += medicines.get(i).getPrice() * quantities.get(i);
        }
        double discount = member ? subtotal * PharmacyConstants.MEMBER_DISCOUNT : 0;
        double gst = (subtotal - discount) * PharmacyConstants.GST_RATE;
        System.out.printf("Subtotal : Rs.%8.2f%n", subtotal);
        System.out.printf("Discount : Rs.%8.2f%n", discount);
        System.out.printf("GST 5%%   : Rs.%8.2f%n", gst);
        System.out.printf("TOTAL    : Rs.%8.2f%n", calculateAmount());
        System.out.println("Payment  : " + paymentMethod);
        System.out.println("===================================\n");
    }
}
