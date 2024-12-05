package shopping.system;

import javafx.application.Application;
import javafx.stage.Stage;
import dao.ProductDAO;
import dao.UserDAO;
import controller.LoginController;
import controller.ProductController;
import view.LoginView;
import view.ProductView;
import view.RegisterView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:2006/shopping_system", "postgres", "1234567m");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize View
//        LoginView loginView = new LoginView(primaryStage);
        RegisterView registerView = new RegisterView(primaryStage);
//        ProductView productView = new ProductView(primaryStage);

        // Initialize DAOs
        UserDAO userDAO = new UserDAO(connection);
//        ProductDAO productDAO = new ProductDAO(connection);

        // Initialize Controllers
//        new LoginController(loginView, userDAO);
        new LoginController(registerView, userDAO);
//        new ProductController(productDAO, productView);

        primaryStage.setTitle("Shopping System");
        primaryStage.show();
    }
}
