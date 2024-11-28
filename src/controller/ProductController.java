package controller;

import dao.ProductDAO;
import model.Product;
import model.User;
import dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        try {
            Connection connection = DatabaseConnection.connect();
            this.productDAO = new ProductDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add product - only for admin
    public void addProduct(Product product, User loggedInUser) throws SQLException {
        if (loggedInUser != null && "admin".equalsIgnoreCase(loggedInUser.getRole())) {
            productDAO.addProduct(product);
        } else {
            System.out.println("Only admins can add products.");
        }
    }

    // Delete product - only for admin
    public void deleteProduct(int productId, User loggedInUser) throws SQLException {
        if (loggedInUser != null && "admin".equalsIgnoreCase(loggedInUser.getRole())) {
            productDAO.deleteProduct(productId);
        } else {
            System.out.println("Only admins can delete products.");
        }
    }

    // Get all products
    public void getAllProducts() {
        productDAO.getAllProducts().forEach(System.out::println);
    }

    // Get a product by ID
    public void getProductById(int id) throws SQLException {
        Product product = productDAO.getProductById(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found!");
        }
    }

    // Update a product
    public void updateProduct(int id, String name, String description, double price, int quantityInStock) throws SQLException {
        Product product = new Product(id, name, description, price, quantityInStock);
        productDAO.updateProduct(product);
        System.out.println("Product updated successfully.");
    }

    // Get products by name (if your DAO has this method)
    public void getProductsByName(String name) throws SQLException {
        List<Product> products = productDAO.getProductsByName(name);
        if (products.isEmpty()) {
            System.out.println("No products found with the name: " + name);
        } else {
            products.forEach(System.out::println);
        }
    }

    // Get products within a price range
    public void getProductsByPriceRange(double minPrice, double maxPrice) throws SQLException {
        List<Product> products = productDAO.getProductsByPriceRange(minPrice, maxPrice);
        if (products.isEmpty()) {
            System.out.println("No products found in the price range: " + minPrice + " - " + maxPrice);
        } else {
            products.forEach(System.out::println);
        }
    }
}
