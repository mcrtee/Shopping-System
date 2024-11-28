package controller;

import dao.ProductDAO;
import model.Product;
import dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private ProductDAO productDAO;

    public ProductController() {
        try {
            Connection connection = DatabaseConnection.connect();
            this.productDAO = new ProductDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new product
    public void addProduct(int id, String name, String description, double price, int quantityInStock) throws SQLException {
        Product product = new Product(id, name, description, price, quantityInStock);
        productDAO.addProduct(product);
    }

    // Get all products
    public void getAllProducts() throws SQLException {
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            products.forEach(System.out::println);
        }
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


    //     Delete a product by ID
    public void deleteProduct(int id) throws SQLException {
        boolean isDeleted = productDAO.deleteProduct(id);
        if (isDeleted) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found!");
        }
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
