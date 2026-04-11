/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BookStoreApp;

import java.util.ArrayList;

public class Customer extends User {
    private int points;
    private CustomerState state;

    public Customer(String username, String password, int points) {
        super(username, password);
        this.points = points;
        updateState();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        updateState();
    }

    public String getStatus() {
        return state.getStatus();
    }

    public double buyBooks(ArrayList<Book> books) {
        return state.buy(this, books);
    }

    public double redeemAndBuyBooks(ArrayList<Book> books) {
        return state.redeem(this, books);
    }

    public void updateState() {
        if (points >= 1000) state = new GoldState();
        else state = new SilverState();
    }

    @Override
    public String toString() {
        return username + "," + password + "," + points; 
    }
}
