// ProductController.java
package controller;

import dao.ProductDAO;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Product;
import view.AdminView;
import view.ShoppingView;

import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private final ProductDAO productDAO;
    private final ShoppingView shoppingView;

    public ProductController(ProductDAO productDAO, ShoppingView shoppingView) {
        this.productDAO = productDAO;
        this.shoppingView = shoppingView;

        // Initialize button actions
        shoppingView.getViewCartButton().setOnAction(e -> loadProducts());
//        shoppingView.getLogoutButton().setOnAction(e -> handleLogout());
        shoppingView.getSearchButton().setOnAction(e -> {
            try {
                searchProducts();
            } catch (SQLException ex) {
                showAlert("Error", "Failed to search products: " + ex.getMessage());
            }
        });
    }

    // Load all products and display them in the shopping view
    public void loadProducts() {
        List<Product> products = productDAO.getAllProducts();
        if (products != null && !products.isEmpty()) {
            shoppingView.displayProducts(products);
        } else {
            showAlert("No Products Found", "No products are available at the moment.");
        }
    }


    // Search for products based on the name entered in a TextField
    private void searchProducts() throws SQLException {
        String searchQuery = shoppingView.getSearchTextField().getText();
        if (searchQuery.isEmpty()) {
            showAlert("Search Error", "Please enter a product name to search.");
            return;
        }

        List<Product> products = productDAO.getProductsByName(searchQuery);
        if (products != null && !products.isEmpty()) {
            shoppingView.displayProducts(products);
        } else {
            showAlert("No Results", "No products found for: " + searchQuery);
        }
    }

    // Show an alert dialog for notifications
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
