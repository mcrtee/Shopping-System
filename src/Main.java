import controller.ProductController;
import dao.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
            // Initialize the controller
            ProductController productController = new ProductController();

            // Add a new product
            productController.addProduct(1, "Laptop", "A high-end laptop", 1500.00, 10);

            // Get all products
            productController.getAllProducts();

            // Get product by ID
            productController.getProductById(1);
        }
    }

