package model;

import java.util.List;

public class Order {
    private int id;
    private int userId;
    private List<Product> productList;
    private double totalAmount;
    private String orderStatus;  // E.g., "Pending", "Completed", etc.

    // Getters and Setters
}
