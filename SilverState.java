/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BookStoreApp;

import java.util.ArrayList;

public class SilverState implements CustomerState {

    public String getStatus() {
        return "Silver";
    }

    public double buy(Customer c, ArrayList<Book> books) {
        double total = 0;
        for (Book b : books) total += b.getPrice();

        c.setPoints(c.getPoints() + (int)(total * 10));
        return total;
    }

    public double redeem(Customer c, ArrayList<Book> books) {
        double total = 0;
        for (Book b : books) total += b.getPrice();

        double discount = c.getPoints() / 100.0;
        if (discount > total) discount = total;

        total -= discount;

        int pts = c.getPoints() - (int)(discount * 100);
        pts += (int)(total * 10);
        c.setPoints(pts);

        return total;
    }
}
