package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private User user;
    private List<Product> products;
    private LocalDateTime orderDate;
    private String status;

    public Order(int orderId, User user, List<Product> products) {
        this.orderId = orderId;
        this.user = user;
        this.products = products != null ? products : new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = "Pending";
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Utility Methods
    public double calculateTotalCost() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
