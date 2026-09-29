# R24SA018 – Hospital Pharmacy Management System

**Student:** KANIMIREDDY JAHNAVI  
**SRN:** R24SA018  
**Program:** B.Sc. (BSTCs), Semester V  
**Subject:** Java Programming  
**Project:** Hospital Pharmacy Management System

## 1. Project Overview

This is a console-based Hospital Pharmacy Management System developed in Java. It allows a pharmacist to view medicines, search medicines, create customer bills, calculate discounts and GST, update stock, and generate a low-stock report.

The project is designed specifically around the mandatory Java OOP requirements in the REVA University practical assignment.

## 2. How to Compile and Run

From the project root:

```text
javac -d out src/com/reva/pharmacy/model/*.java src/com/reva/pharmacy/service/*.java src/com/reva/pharmacy/app/*.java
java -cp out com.reva.pharmacy.app.Main
```

## 3. Package Structure

```text
src/
└── com/reva/pharmacy/
    ├── app/
    │   └── Main.java
    ├── model/
    │   ├── Customer.java
    │   ├── Medicine.java
    │   ├── MedicineCategory.java
    │   ├── Payable.java
    │   ├── PaymentMethod.java
    │   ├── Person.java
    │   ├── Pharmacist.java
    │   ├── PharmacyConstants.java
    │   └── Sale.java
    └── service/
        └── PharmacyService.java
```

## 4. Feature Traceability

| Requirement | Implementation |
|---|---|
| 1. 3–4 encapsulated classes | `Person`, `Customer`, `Pharmacist`, `Medicine`, `Sale` use private fields + getters/setters |
| 2. Data types, final constants, scope | `PharmacyConstants`, instance fields, local variables, static counters |
| 3. Operators + precedence | `Main.processSale()` uses `price * quantity + 0.0`; multiplication precedes addition |
| 4. Type conversion/casting | `(int) total` in `Main.processSale()` and `Sale.calculateAmount(boolean)` |
| 5. Enum | `MedicineCategory`, `PaymentMethod` |
| 6. Control + jump statements | `if/else`, `switch`, `while`, `for`, `break`, `continue`, `return` |
| 7. Arrays of objects | `Medicine[]` in `PharmacyService` |
| 8. Console I/O + formatted output | `Scanner`, `printf`, `String.format` |
| 9. Constructor overloading | Default + parameterized constructors in `Person`, `Customer`, `Pharmacist`, `Medicine` |
| 10. Method overloading | `Sale.addItem(Medicine)` / `addItem(Medicine,int)` and `PharmacyService.searchMedicine(...)` |
| 11. Static fields/methods | `Medicine.totalMedicinesCreated`, `Medicine.getTotalMedicinesCreated()`, static counters in `Main` and `Sale` |
| 12. `this` reference | Constructors use `this(...)` and `this.field` |
| 13. String methods | `trim()`, `equalsIgnoreCase()`, `toLowerCase()`, `contains()` |
| 14. Base + 2 subclasses | `Person` → `Customer`, `Pharmacist` |
| 15. `super` keyword | `Customer` and `Pharmacist` constructors call `super(...)`; `Pharmacist.displaySummary()` calls `super.displaySummary()` |
| 16. Dynamic binding | `Person personReference = pharmacist; personReference.displaySummary();` |
| 17. Abstract class/method | `Person` and abstract `getRole()` |
| 18. Interface | `Payable` implemented by `Sale`; `Payable payable = sale;` |
| 19. Object methods | `Medicine.toString()` and `equals()` overridden; `hashCode()` also overridden |
| 20. Final class/method | `PharmacyConstants` is final; comment explains why |
| 21. Custom packages | `com.reva.pharmacy.model`, `com.reva.pharmacy.service`, `com.reva.pharmacy.app` |

## 5. Main Functions

1. View all medicines.
2. Search for a medicine.
3. Create a customer bill.
4. Apply optional membership discount.
5. Calculate 5% GST.
6. Select CASH, UPI, or CARD payment.
7. Automatically reduce medicine stock after sale.
8. Show low-stock medicines.
9. Display pharmacist details.

## 6. OOP Design

`Person` is the abstract base class. `Customer` and `Pharmacist` inherit from it. `Sale` implements the `Payable` interface. `Medicine` demonstrates encapsulation, static object counting, `toString()`, `equals()`, and `hashCode()`.

## 7. Viva Preparation

**Q1. Why is `Person` abstract?**  
A: It represents a common person structure, while `Customer` and `Pharmacist` provide their own role through the abstract `getRole()` method.

**Q2. Where is dynamic binding used?**  
A: `Person personReference = pharmacist; personReference.displaySummary();` calls the overridden `Pharmacist.displaySummary()` at runtime.

**Q3. Why use an interface?**  
A: `Payable` defines payment-related behavior. `Sale` implements it, and the object is accessed through a `Payable` reference.

**Q4. Where is method overloading?**  
A: `addItem()` has two versions and `searchMedicine()` has two versions with different parameter lists.

**Q5. Where is encapsulation?**  
A: Class fields are private and are accessed through public getter/setter methods.

**Q6. Why is `PharmacyConstants` final?**  
A: It is a utility class containing fixed application constants and should not be inherited.
