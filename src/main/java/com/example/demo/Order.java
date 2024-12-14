package com.example.demo;

import java.util.List;

public class Order {
    private List<Product> products;
    private double totalPrice;

    public Order(List<Product> products) {
        this.products = products;
        this.totalPrice = calculateTotalPrice();
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    // Getters and setters
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
