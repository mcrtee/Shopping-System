package controller;

import dao.ProductDAO;
import model.Product;
import view.ProductView;

import java.util.List;

public class ProductController {
    private final ProductDAO productDAO;
    private final ProductView productView;

    public ProductController(ProductDAO productDAO, ProductView productView) {
        this.productDAO = productDAO;
        this.productView = productView;

        // Initialize event handlers
        productView.getSearchButton().setOnAction(e -> searchProducts());
        loadAllProducts();
    }

    /**
     * Load and display all products from the database.
     */
    private void loadAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            productView.showMessage("No products found.");
        } else {
            productView.displayProducts(products);
        }
    }

    /**
     * Search products based on user input from the search field.
     */
    private void searchProducts() {
        String query = productView.getSearchQuery();

        if (query.isEmpty()) {
            productView.showMessage("Search field is empty. Showing all products.");
            loadAllProducts();
        } else {
            List<Product> products = productDAO.searchProducts(query);
            if (products.isEmpty()) {
                productView.showMessage("No matching products found for: " + query);
            } else {
                productView.displayProducts(products);
            }
        }
    }
}
