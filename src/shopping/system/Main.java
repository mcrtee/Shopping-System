package shopping.system;

import controller.AdminController;
import controller.LoginController;
import dao.ProductDAO;
import dao.UserDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import view.AdminView;
import view.LoginView;
import view.RegisterView;
import view.ShoppingView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:2006/shopping_system",
                    "postgres",
                    "1234567m"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1); // Exit if the connection fails.
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Regulator regulator = new LoginDisplay(connection);
        regulator.display(primaryStage);
    }
}
