# Java Bookstore Management System

## Overview
This project is a GUI-based bookstore system developed using Java Swing as part of a group project.

It allows an admin (owner) to manage books and customers, and allows customers to browse, purchase, and redeem points.


## Features

### Admin (Owner)
- Add and remove books
- Manage book pricing
- Create and delete customer accounts

### Customer
- Login system
- Browse and purchase books
- Earn points (10 points per $1 spent)
- Redeem points for discounts
- Automatic membership upgrade based on points


## Design Concepts
- Object-Oriented Programming (OOP)
- State Design Pattern
- GUI development using Java Swing
- Role-based system (Owner vs Customer)


## State System
Customers have two states:
- Silver (default)
- Gold (1000+ points)

The system automatically updates the state based on points.


## Technologies Used
- Java
- Java Swing


## Example Use Case: Buying Books

A customer can browse and purchase books using either direct payment or accumulated reward points.

### Standard Purchase
- Customer selects one or more books
- System calculates total cost
- Customer earns 10 points per dollar spent
- System updates customer status:
  - Silver (< 1000 points)
  - Gold (≥ 1000 points)

### Redeeming Points
- Customer selects books and chooses to redeem points
- Every 100 points reduces the cost by $1
- System updates remaining points after redemption
- Customer status is recalculated automatically


## What I Learned
- Building GUI applications with Java Swing
- Applying the State Design Pattern
- Structuring multi-class systems
- Implementing user interaction and business logic


## Java Files 
[Book.java](https://github.com/user-attachments/files/26641477/Book.java)
[User.java](https://github.com/user-attachments/files/26641476/User.java)
[SilverState.java](https://github.com/user-attachments/files/26641475/SilverState.java)
[Owner.java](https://github.com/user-attachments/files/26641474/Owner.java)
[GoldState.java](https://github.com/user-attachments/files/26641473/GoldState.java)
[CustomerState.java](https://github.com/user-attachments/files/26641472/CustomerState.java)
[Customer.java](https://github.com/user-attachments/files/26641470/Customer.java)
[BookStoreGUI.java](https://github.com/user-attachments/files/26641469/BookStoreGUI.java)
[BookStoreApp.java](https://github.com/user-attachments/files/26641468/BookStoreApp.java)
[BookStore.java](https://github.com/user-attachments/files/26641467/BookStore.java)
