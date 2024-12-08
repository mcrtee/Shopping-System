// AdminController.java
package controller;

import dao.ProductDAO;
import model.Product;
import view.AdminView;

import java.util.List;

public class ProductController {
    public  ProductDAO productDAO;
    public AdminView adminView;

    public ProductController(ProductDAO productDAO, AdminView adminView) {
        this.productDAO = productDAO;
        this.adminView = adminView;

        // Bind event listeners to view actions
        adminView.getAddProductButton().setOnAction(e -> addProduct());
        adminView.getDeleteProductButton().setOnAction(e -> deleteProduct());
        adminView.getUpdateProductButton().setOnAction(e -> updateProduct());

        // Load products initially
        loadProducts();
    }


    private void loadProducts() {
        List<Product> products = productDAO.getAllProducts();
        adminView.displayProducts(products);
    }

    private void addProduct() {
        Product product = adminView.getProductDetailsFromForm();
        if (product != null && productDAO.addProduct(product)) {
            adminView.showMessage("Product added successfully.");
            loadProducts();
        } else {
            adminView.showMessage("Failed to add product. Please check your input.");
        }
    }

    private void deleteProduct() {
        int productId = adminView.getSelectedProductId();
        if (productId != -1 && productDAO.deleteProduct(productId)) {
            adminView.showMessage("Product deleted successfully.");
            loadProducts();
        } else {
            adminView.showMessage("Failed to delete product. Please select a valid product.");
        }
    }

    private void updateProduct() {
        Product product = adminView.getProductDetailsFromForm();
        if (product != null && productDAO.updateProduct(product)) {
            adminView.showMessage("Product updated successfully.");
            loadProducts();
        } else {
            adminView.showMessage("Failed to update product. Please check your input.");
        }
    }
}
