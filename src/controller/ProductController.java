package controller;

import dao.ProductDAO;
import model.Product;

import dao.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

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

    // Method to add a new product
    public void addProduct(int id, String name, String description, double price, int quantityInStock) {
        Product product = new Product(id, name, description, price, quantityInStock);
        productDAO.addProduct(product);
    }

    // Method to get all products
    public void getAllProducts() {
        productDAO.getAllProducts().forEach(System.out::println);
    }

    // Method to get a product by ID
    public void getProductById(int id) {
        Product product = productDAO.getProductById(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found!");
        }
    }
}
