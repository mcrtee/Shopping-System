package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private ListView<Product> productListView;

    @FXML
    private Button addButton;

    @FXML
    private Button placeOrderButton;

    private List<Product> cart = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    public MainController() {
        // Add some sample products
        products.add(new Product(1, "Product 1", 10.0, 10));
        products.add(new Product(2, "Product 2", 15.0, 5));
        products.add(new Product(3, "Product 3", 20.0, 2));
    }

    @FXML
    public void initialize() {
        productListView.setItems(FXCollections.observableArrayList(products));
    }

    @FXML
    public void addToCart() {
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            cart.add(selectedProduct);
            System.out.println("Added to cart: " + selectedProduct.getName());
        }
    }

    @FXML
    public void placeOrder() {
        if (!cart.isEmpty()) {
            Order order = new Order(cart);
            System.out.println("Order placed. Total: " + order.getTotalPrice());
        } else {
            System.out.println("Cart is empty. Add products to the cart first.");
        }
    }
}
