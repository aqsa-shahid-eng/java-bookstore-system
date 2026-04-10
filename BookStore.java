/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BookStoreApp;

import java.io.*;
import java.util.*;

public class BookStore {
    private ArrayList<Book> books;
    private ArrayList<Customer> customers;
    private Owner owner;

    public BookStore() {
        books = new ArrayList<>();
        customers = new ArrayList<>();
        owner = new Owner("admin", "admin");
    }

    public void addBook(Owner owner, Book book) {
        books.add(book);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public Owner getOwner() {
        return owner;
    }

    public void deleteBook(String name) {
        books.removeIf(b -> b.getName().equals(name));
    }

    public void addCustomer(Owner owner, Customer c) {
        customers.add(c);
    }

    public void deleteCustomer(Owner owner, String username) {
        customers.removeIf(c -> c.getUsername().equals(username));
    }

    public User login(String username, String password) {
        if (owner.login(username, password)) return owner;

        for (Customer c : customers) {
            if (c.login(username, password)) return c;
        }
        return null;
    }

    private String getPath(String fileName) {
        return System.getProperty("user.dir") + "/" + fileName;
    }

    public void loadBooks() {
        books.clear();
        try {
            Scanner in = new Scanner(new File(getPath("books.txt")));
            while (in.hasNextLine()) {
                String[] p = in.nextLine().split(",");
                books.add(new Book(p[0], Double.parseDouble(p[1])));
            }
            in.close();
        } catch (Exception e) {
            System.out.println("No books file yet");
        }
    }

    public void loadCustomer() {
        customers.clear();
        try {
            Scanner in = new Scanner(new File(getPath("customers.txt")));
            while (in.hasNextLine()) {
                String[] p = in.nextLine().split(",");
                customers.add(new Customer(p[0], p[1], Integer.parseInt(p[2])));
            }
            in.close();
        } catch (Exception e) {
            System.out.println("No customers file yet");
        }
    }

    public void saveBooks() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(getPath("books.txt")));
            for (Book b : books) {
                out.println(b.getName() + "," + b.getPrice());
            }
            out.close();
        } catch (Exception e) {
            System.out.println("Error saving books");
        }
    }

    public void saveCustomers() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(getPath("customers.txt")));
            for (Customer c : customers) {
                out.println(c.getUsername() + "," + c.getPassword() + "," + c.getPoints());
            }
            out.close();
        } catch (Exception e) {
            System.out.println("Error saving customers");
        }
    }
}
