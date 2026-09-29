package com.reva.pharmacy.app;

import com.reva.pharmacy.model.*;
import com.reva.pharmacy.service.PharmacyService;

import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static int customerIdCounter = 1; // static variable

    public static void main(String[] args) {
        Pharmacist pharmacist = new Pharmacist(1, "Jahnavi", "9876543210", "KAR-PH-2048");
        PharmacyService service = new PharmacyService(pharmacist, 20);
        seedMedicines(service);

        // Parent reference holding a child object: dynamic binding.
        Person personReference = pharmacist;
        personReference.displaySummary();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> service.displayMedicines();
                case 2 -> searchMedicine(service);
                case 3 -> processSale(service);
                case 4 -> service.lowStockReport(10);
                case 5 -> pharmacist.displaySummary();
                case 6 -> {
                    System.out.println("Total medicine objects created: " + Medicine.getTotalMedicinesCreated());
                    running = false;
                    System.out.println("Thank you for using " + PharmacyConstants.PHARMACY_NAME + "!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        SCANNER.close();
    }

   private static void seedMedicines(PharmacyService service) {
    service.addMedicine(new Medicine(101, "Paracetamol", MedicineCategory.TABLET, 25.50, 50, "12/2027"));
    service.addMedicine(new Medicine(102, "Amoxicillin", MedicineCategory.CAPSULE, 80.00, 8, "06/2027"));
    service.addMedicine(new Medicine(103, "Cough Syrup", MedicineCategory.SYRUP, 120.00, 15, "03/2027"));
    service.addMedicine(new Medicine(104, "Antiseptic Cream", MedicineCategory.OINTMENT, 65.00, 6, "11/2026"));
    service.addMedicine(new Medicine(105, "Vitamin D3", MedicineCategory.CAPSULE, 150.00, 30, "08/2028"));

    // Additional medicines
    service.addMedicine(new Medicine(106, "Cetirizine", MedicineCategory.TABLET, 35.00, 25, "09/2027"));
    service.addMedicine(new Medicine(107, "Azithromycin", MedicineCategory.TABLET, 95.00, 12, "05/2027"));
    service.addMedicine(new Medicine(108, "Omeprazole", MedicineCategory.CAPSULE, 45.00, 20, "10/2027"));
    service.addMedicine(new Medicine(109, "Ibuprofen", MedicineCategory.TABLET, 40.00, 35, "07/2028"));
    service.addMedicine(new Medicine(110, "ORS Solution", MedicineCategory.SYRUP, 30.00, 40, "02/2027"));
    service.addMedicine(new Medicine(111, "Calamine Lotion", MedicineCategory.OINTMENT, 75.00, 18, "01/2028"));
    service.addMedicine(new Medicine(112, "Multivitamin", MedicineCategory.CAPSULE, 180.00, 25, "06/2028"));
    service.addMedicine(new Medicine(113, "Pantoprazole", MedicineCategory.TABLET, 55.00, 22, "11/2027"));
    service.addMedicine(new Medicine(114, "Antacid Gel", MedicineCategory.SYRUP, 90.00, 14, "04/2027"));
    service.addMedicine(new Medicine(115, "Mupirocin", MedicineCategory.OINTMENT, 110.00, 10, "12/2027"));
}

    private static void printMenu() {
        System.out.println("\n====== " + PharmacyConstants.PHARMACY_NAME + " ======");
        System.out.println("1. View all medicines");
        System.out.println("2. Search medicine");
        System.out.println("3. Create customer bill");
        System.out.println("4. Low-stock report");
        System.out.println("5. Pharmacist details");
        System.out.println("6. Exit");
    }

    private static void searchMedicine(PharmacyService service) {
        String name = readString("Enter medicine name: ").trim(); // trim String method
        if (name.isEmpty()) {
            System.out.println("Medicine name cannot be empty.");
            return;
        }
        Medicine medicine = service.searchMedicine(name);
        if (medicine != null) {
            System.out.println("Medicine found: " + medicine);
            if (medicine.getName().toLowerCase().contains("acid")) {
                System.out.println("Note: Search matched an acid-related medicine.");
            }
        } else {
            System.out.println("Medicine not found.");
        }
    }

    private static void processSale(PharmacyService service) {
        String name = readString("Customer name: ").trim();
        String phone = readString("Phone number: ").trim();
        Customer customer = new Customer(customerIdCounter++, name, phone, "REVA Area");
        Sale sale = new Sale(customer);

        boolean adding = true;
        while (adding) {
            String medicineName = readString("Medicine name (or 'done'): ").trim();
            if (medicineName.equalsIgnoreCase("done")) {
                break;
            }
            Medicine medicine = service.searchMedicine(medicineName);
            if (medicine == null) {
                System.out.println("Medicine not found.");
                continue;
            }

            int quantity = readInt("Quantity: ");
            if (quantity <= 0 || quantity > medicine.getStock()) {
                System.out.println("Invalid quantity or insufficient stock.");
                continue;
            }

            // Operator precedence: multiplication occurs before addition.
            double estimatedLineAmount = medicine.getPrice() * quantity + 0.0;
            System.out.printf("Line amount: Rs. %.2f%n", estimatedLineAmount);
            sale.addItem(medicine, quantity);

            String more = readString("Add another medicine? (y/n): ").trim();
            if (more.equalsIgnoreCase("n")) {
                adding = false;
            }
        }

        if (sale.getMedicines().isEmpty()) {
            System.out.println("No items added. Bill cancelled.");
            return;
        }

        String memberAnswer = readString("Is customer a member? (y/n): ").trim();
        sale.setMember(memberAnswer.equalsIgnoreCase("y"));

        String payment = readString("Payment method (cash/upi/card): ").trim().toUpperCase();
        try {
            sale.setPaymentMethod(PaymentMethod.valueOf(payment));
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid payment method. CASH selected.");
            sale.setPaymentMethod(PaymentMethod.CASH);
        }

        // Interface reference accessing the implemented class.
        Payable payable = sale;
        double total = payable.calculateAmount();
        System.out.printf("Final amount: Rs. %.2f%n", total);
        payable.printReceipt();

        // Explicit type conversion: double -> int.
        int roundedAmount = (int) total;
        System.out.println("Rounded amount for cash display: Rs. " + roundedAmount);
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readString(prompt).trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }
}
