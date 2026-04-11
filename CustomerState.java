/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BookStoreApp;

import java.util.ArrayList;

public interface CustomerState {
    String getStatus();
    double buy(Customer c, ArrayList<Book> books);
    double redeem(Customer c, ArrayList<Book> books);
}
