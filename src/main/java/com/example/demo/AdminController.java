
package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminController {

    private ProductDAO productDAO;

    @FXML
    private TextField productIdField;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productPriceField;

    @FXML
    private TextField productStockField;

    @FXML
    TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, Integer> stockColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label welcomeLabel;


    public AdminController() throws SQLException {
        // Assume the connection is established in another part of the code
        Connection connection = DatabaseConnection.getConnection(); // Change this with your actual DB connection
        this.productDAO = new ProductDAO(connection);
    }



    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        loadProducts();
    }

    @FXML
    public void addProduct() {
        int id = Integer.parseInt(productIdField.getText());
        String name = productNameField.getText();
        double price = Double.parseDouble(productPriceField.getText());
        int stock = Integer.parseInt(productStockField.getText());

        Product newProduct = new Product(id, name, price, stock);
        boolean success = productDAO.addProduct(newProduct);

        if (success) {
            showAlert("Product Added", "Product has been added successfully.", AlertType.INFORMATION);
        } else {
            showAlert("Error", "Failed to add the product.", AlertType.ERROR);
        }

        loadProducts(); // Reload the product list
    }

    @FXML
    public void updateProduct() {
        int id = Integer.parseInt(productIdField.getText());
        String name = productNameField.getText();
        double price = Double.parseDouble(productPriceField.getText());
        int stock = Integer.parseInt(productStockField.getText());

        Product updatedProduct = new Product(id, name, price, stock);
        boolean success = productDAO.updateProduct(updatedProduct);

        if (success) {
            showAlert("Product Updated", "Product has been updated successfully.", AlertType.INFORMATION);
        } else {
            showAlert("Error", "Failed to update the product.", AlertType.ERROR);
        }

        loadProducts(); // Reload the product list
    }

    @FXML
    public void deleteProduct() {
        int productId = Integer.parseInt(productIdField.getText());
        boolean success = productDAO.deleteProduct(productId);

        if (success) {
            showAlert("Product Deleted", "Product has been deleted successfully.", AlertType.INFORMATION);
        } else {
            showAlert("Error", "Failed to delete the product.", AlertType.ERROR);
        }

        loadProducts(); // Reload the product list
    }

    void loadProducts() {
        List<Product> products = productDAO.getAllProducts();
        productTable.getItems().clear();
        productTable.getItems().addAll(products);
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void initializeWithUser(User user) {

    }
}


