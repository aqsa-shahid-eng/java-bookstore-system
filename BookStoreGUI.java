/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BookStoreApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BookStoreGUI extends JFrame {
    private BookStore store;
    private JPanel panel;

    private Color bgColor = new Color(242, 238, 230);
    private Color cardColor = new Color(250, 250, 248);
    private Color buttonColor = new Color(140, 170, 150);
    private Color textColor = new Color(30, 60, 40);

    private Font mainFont = new Font("Courier New", Font.PLAIN, 14);
    private Font titleFont = new Font("Courier New", Font.BOLD, 18);

    public BookStoreGUI() {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}

        store = new BookStore();
        store.loadBooks();
        store.loadCustomer();

        setTitle("Book Store");
        setSize(700, 500);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setBackground(bgColor);
        add(panel);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                store.saveBooks();
                store.saveCustomers();
                dispose();
            }
        });

        loginScreen();
        setVisible(true);
    }

    public void clear() {
        panel.removeAll();
        panel.repaint();
        panel.revalidate();
        panel.setBackground(bgColor);
    }

    private void styleButton(JButton b) {
        b.setBackground(buttonColor);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(mainFont);
        b.setOpaque(true);
        b.setBorderPainted(false);
    }

    private JLabel styledLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(mainFont);
        l.setForeground(textColor);
        return l;
    }

    //  LOGIN 
    public void loginScreen() {
        clear();
        panel.setLayout(new GridBagLayout());

        JPanel box = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(cardColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };

        box.setOpaque(false);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("Login");
        title.setFont(titleFont);
        title.setForeground(textColor);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(title);

        box.add(Box.createVerticalStrut(20));

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        userPanel.setOpaque(false);
        JTextField userField = new JTextField(12);
        userField.setFont(mainFont);
        userPanel.add(styledLabel("Username:"));
        userPanel.add(userField);
        box.add(userPanel);

        box.add(Box.createVerticalStrut(12));

        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        passPanel.setOpaque(false);
        JPasswordField passField = new JPasswordField(12);
        passField.setFont(mainFont);
        passPanel.add(styledLabel("Password:"));
        passPanel.add(passField);
        box.add(passPanel);

        box.add(Box.createVerticalStrut(20));

        JButton loginButton = new JButton("Login");
        styleButton(loginButton);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(loginButton);

        box.add(Box.createVerticalStrut(12));

        JLabel msg = styledLabel("");
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(msg);

        panel.add(box);

        loginButton.addActionListener(e -> {
            User u = store.login(userField.getText(),
                    new String(passField.getPassword()));

            if (u instanceof Owner) ownerHome();
            else if (u instanceof Customer) customerHome((Customer) u);
            else msg.setText("Invalid login");
        });
    }

    //  OWNER HOME 
    public void ownerHome() {
        clear();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(80));

        JLabel title = new JLabel("Owner Dashboard");
        title.setFont(titleFont);
        title.setForeground(textColor);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        panel.add(Box.createVerticalStrut(30));

        JButton books = new JButton("Manage Books");
        JButton customers = new JButton("Manage Customers");
        JButton logout = new JButton("Logout");

        styleButton(books);
        styleButton(customers);
        styleButton(logout);

        books.setAlignmentX(Component.CENTER_ALIGNMENT);
        customers.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(books);
        panel.add(Box.createVerticalStrut(12));
        panel.add(customers);
        panel.add(Box.createVerticalStrut(12));
        panel.add(logout);

        books.addActionListener(e -> ownerBooksScreen());
        customers.addActionListener(e -> ownerCustomersScreen());
        logout.addActionListener(e -> loginScreen());
    }

    //  OWNER BOOKS 
    public void ownerBooksScreen() {
        clear();
        panel.setLayout(new BorderLayout());

        String[] cols = {"Book Name", "Price"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        for (Book b : store.getBooks()) {
            model.addRow(new Object[]{b.getName(), String.format("$%.2f", b.getPrice())});
        }

        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JTextField nameField = new JTextField(10);
        JTextField priceField = new JTextField(10);

        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        JButton back = new JButton("Back");

        styleButton(add);
        styleButton(delete);
        styleButton(back);

        JPanel top = new JPanel();
        top.setBackground(bgColor);
        top.add(styledLabel("Name"));
        top.add(nameField);
        top.add(styledLabel("Price"));
        top.add(priceField);
        top.add(add);

        JPanel bottom = new JPanel();
        bottom.setBackground(bgColor);
        bottom.add(delete);
        bottom.add(back);

        panel.add(top, BorderLayout.NORTH);
        panel.add(bottom, BorderLayout.SOUTH);

        add.addActionListener(e -> {
            try {
                store.addBook(store.getOwner(),
                        new Book(nameField.getText(), Double.parseDouble(priceField.getText())));
                store.saveBooks();
                ownerBooksScreen();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        });

        delete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                store.deleteBook((String) model.getValueAt(row, 0));
                store.saveBooks();
                ownerBooksScreen();
            }
        });

        back.addActionListener(e -> ownerHome());
    }

    //  OWNER CUSTOMERS 
    public void ownerCustomersScreen() {
        clear();
        panel.setLayout(new BorderLayout());

        String[] cols = {"Username", "Password", "Points"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        for (Customer c : store.getCustomers()) {
            model.addRow(new Object[]{c.getUsername(), c.getPassword(), c.getPoints()});
        }

        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JTextField userField = new JTextField(10);
        JTextField passField = new JTextField(10);

        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        JButton back = new JButton("Back");

        styleButton(add);
        styleButton(delete);
        styleButton(back);

        JPanel top = new JPanel();
        top.setBackground(bgColor);
        top.add(styledLabel("Username"));
        top.add(userField);
        top.add(styledLabel("Password"));
        top.add(passField);
        top.add(add);

        JPanel bottom = new JPanel();
        bottom.setBackground(bgColor);
        bottom.add(delete);
        bottom.add(back);

        panel.add(top, BorderLayout.NORTH);
        panel.add(bottom, BorderLayout.SOUTH);

        add.addActionListener(e -> {
            store.addCustomer(store.getOwner(),
                    new Customer(userField.getText(), passField.getText(), 0));
            store.saveCustomers();
            ownerCustomersScreen();
        });

        delete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                store.deleteCustomer(store.getOwner(),
                        (String) model.getValueAt(row, 0));
                store.saveCustomers();
                ownerCustomersScreen();
            }
        });

        back.addActionListener(e -> ownerHome());
    }

    // CUSTOMER 
    public void customerHome(Customer customer) {
        clear();
        panel.setLayout(new BorderLayout());

        JLabel info = styledLabel(
                "Welcome " + customer.getUsername() +
                " | Points: " + customer.getPoints() +
                " | Status: " + customer.getStatus()
        );

        panel.add(info, BorderLayout.NORTH);

        String[] cols = {"Book Name", "Price", "Select"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public Class getColumnClass(int c) {
                return c == 2 ? Boolean.class : String.class;
            }
        };

        for (Book b : store.getBooks()) {
            model.addRow(new Object[]{b.getName(), b.getPrice(), false});
        }

        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton buy = new JButton("Buy");
        JButton redeem = new JButton("Redeem and Buy");
        JButton logout = new JButton("Logout");

        styleButton(buy);
        styleButton(redeem);
        styleButton(logout);

        JPanel bottom = new JPanel();
        bottom.setBackground(bgColor);
        bottom.add(buy);
        bottom.add(redeem);
        bottom.add(logout);

        panel.add(bottom, BorderLayout.SOUTH);

        buy.addActionListener(e -> {
            ArrayList<Book> selected = getSelectedBooks(model);
            double cost = customer.buyBooks(selected);
            store.saveCustomers();
            customerCostScreen(customer, cost);
        });

        redeem.addActionListener(e -> {
            ArrayList<Book> selected = getSelectedBooks(model);
            double cost = customer.redeemAndBuyBooks(selected);
            store.saveCustomers();
            customerCostScreen(customer, cost);
        });

        logout.addActionListener(e -> loginScreen());
    }

    private ArrayList<Book> getSelectedBooks(DefaultTableModel model) {
        ArrayList<Book> selected = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean checked = (Boolean) model.getValueAt(i, 2);
            if (checked != null && checked) {
                selected.add(new Book(
                        (String) model.getValueAt(i, 0),
                        Double.parseDouble(model.getValueAt(i, 1).toString())
                ));
            }
        }
        return selected;
    }

    //  COST 
    public void customerCostScreen(Customer customer, double cost) {
        clear();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(100));

        JLabel costLabel = styledLabel("Total Cost: $" + cost);
        costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(costLabel);

        JLabel pointsLabel = styledLabel("Points: " + customer.getPoints());
        pointsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(pointsLabel);

        JLabel statusLabel = styledLabel("Status: " + customer.getStatus());
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(statusLabel);

        panel.add(Box.createVerticalStrut(20));

        JButton logout = new JButton("Logout");
        styleButton(logout);
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(logout);

        logout.addActionListener(e -> loginScreen());
    }
}
