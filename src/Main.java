import controller.ProductController;
import dao.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
            // Initialize the controller
            ProductController productController = new ProductController();

            // Add a new product
            productController.addProduct(2, "Smartphone", "Iphone 16", 1600.00, 15);
            productController.addProduct(1, "Laptop", "Lenovo", 1500.00, 10);

            productController.getProductsByName("Smartphone");
            productController.getProductsByPriceRange(1,1500.00);

            // Get all products
//            productController.getAllProducts();


            // update product
//            productController.updateProduct(2,"Smartphone","Iphone 16", 1600.00, 13);

            // delete product
//            productController.deleteProduct(1);
        // Get all products
//        productController.getAllProducts();

    }
}

