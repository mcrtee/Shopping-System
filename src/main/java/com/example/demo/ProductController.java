package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    @FXML
    ListView<Product> productListView;

    @FXML
    private Button addButton;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Label cartTotalLabel;  // To display the total price of the cart

    @FXML
    private Label cartSizeLabel;  // To display the number of items in the cart

    List<Product> cart = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private User currentUser;
    private ProductDAO productDAO;

    public void initializeWithUser(User user) throws SQLException {
        this.currentUser = user;
        // Assume the connection is established in another part of the code
        Connection connection = DatabaseConnection.getConnection(); // Change this with your actual DB connection
        this.productDAO = new ProductDAO(connection);

        // Initially, update the cart total and size to show an empty cart
        updateCartUI();

        // Load products from the database
        loadProducts();
    }

    @FXML
    public void initialize() {
        // This method is generally called after `initializeWithUser` in JavaFX controllers
        // Setup for TableView columns would go here if used, but for ListView, it's not needed.
    }

    // Method to load products from the database into the ListView
    private void loadProducts() {
        products = productDAO.getAllProducts();
        // Populate the product list
        productListView.setItems(FXCollections.observableArrayList(products));

        // Create custom cell for ListView
        productListView.setCellFactory(param -> new ListCell<Product>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " - $" + item.getPrice());
                }
            }
        });
    }

    @FXML
    public void addToCart() {
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            cart.add(selectedProduct);
            updateCartUI(); // Update the cart UI after adding a product

            // Show an alert when a product is added to the cart
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Product Added");
            alert.setHeaderText("Product Added to Cart");
            alert.setContentText("Added to cart: " + selectedProduct.getName());
            alert.showAndWait();
        }
    }

    @FXML
    public void placeOrder() {
        if (!cart.isEmpty()) {
            Order order = new Order(cart);
            updateCartUI(); // Update the cart UI after placing the order

            // Show order confirmation with total price
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText("Order Placed Successfully");
            alert.setContentText("Your order has been placed. Total: $" + order.getTotalPrice());
            alert.showAndWait();

            // Clear the cart after placing the order
            cart.clear();
        } else {
            // Show an alert if the cart is empty
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Cart Empty");
            alert.setHeaderText("Cart is Empty");
            alert.setContentText("Please add products to your cart before placing an order.");
            alert.showAndWait();
        }
    }

    // Method to update the cart UI
    private void updateCartUI() {
        double totalPrice = cart.stream().mapToDouble(Product::getPrice).sum();
        cartTotalLabel.setText("Total Price: $" + totalPrice);  // Update the cart total label
        cartSizeLabel.setText("Items in Cart: " + cart.size()); // Update the cart size label
    }
}
