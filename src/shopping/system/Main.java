package shopping.system;

import controller.ProductController;
import dao.ProductDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import dao.UserDAO;
import controller.LoginController;
import view.AdminView;
import view.LoginView;

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
        LoginView loginView = new LoginView(primaryStage);
//        RegisterView registerView = new RegisterView(primaryStage);
//        AdminView productView = new AdminView(primaryStage);

        // Initialize DAOs
        UserDAO userDAO = new UserDAO(connection);
//        ProductDAO productDAO = new ProductDAO(connection);

        // Initialize Controllers
        new LoginController(loginView, userDAO);
//        new LoginController(registerView, userDAO);
//        new ProductController(productDAO, productView);

        primaryStage.setTitle("Shopping System");
        primaryStage.show();
    }
}
