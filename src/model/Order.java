package model;

import java.util.List;

public class Order {
    private int orderId;
    private User user;
    private List<Product> products;

    public Order(int orderId, User user, List<Product> products) {
        this.orderId = orderId;
        this.user = user;
        this.products = products;
    }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}


