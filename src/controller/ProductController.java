package controller;

import dao.ProductDAO;
import model.Product;
import model.User;
import view.AdminView;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private final ProductDAO productDAO;
    private final AdminView adminView;

    public ProductController(ProductDAO productDAO, AdminView adminView) {
        this.productDAO = productDAO;
        this.adminView = adminView;

        // Initialize admin-specific event handlers
        adminView.getAddProductButton().setOnAction(e -> addProduct());
        adminView.getDeleteProductButton().setOnAction(e -> deleteProduct());
        adminView.getUpdateProductButton().setOnAction(e -> updateProduct());

        // Load all products initially
        loadAllProducts();
    }

    private void addProduct() {
        Product newProduct = adminView.getProductDetailsFromForm();
        if (newProduct == null) {
            adminView.showMessage("Invalid product details. Please check the input.");
            return;
        }

        boolean isAdded = productDAO.addProduct(newProduct);
        if (isAdded) {
            adminView.showMessage("Product added successfully.");
            loadAllProducts();
        } else {
            adminView.showMessage("Failed to add the product. Please try again.");
        }
    }

    private void updateProduct() {
        Product updatedProduct = adminView.getProductDetailsFromForm();
        if (updatedProduct == null) {
            adminView.showMessage("Invalid product details. Please check the input.");
            return;
        }

        boolean isUpdated = productDAO.updateProduct(updatedProduct);
        if (isUpdated) {
            adminView.showMessage("Product updated successfully.");
            loadAllProducts();
        } else {
            adminView.showMessage("Failed to update the product. Please try again.");
        }
    }

    private void deleteProduct() {
        int productId = adminView.getSelectedProductId();
        if (productId <= 0) {
            adminView.showMessage("Please select a valid product to delete.");
            return;
        }

        boolean isDeleted = productDAO.deleteProduct(productId);
        if (isDeleted) {
            adminView.showMessage("Product deleted successfully.");
            loadAllProducts();
        } else {
            adminView.showMessage("Failed to delete the product. Please try again.");
        }
    }

    /**
     * Load and display all products from the database.
     */
    private void loadAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            adminView.showMessage("No products found.");
        } else {
            adminView.displayProducts(products);
        }
    }
}
