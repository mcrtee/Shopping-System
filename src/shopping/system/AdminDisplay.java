package shopping.system;

import controller.AdminController;
import controller.LoginController;
import dao.ProductDAO;
import dao.UserDAO;
import javafx.stage.Stage;
import view.AdminView;
import view.LoginView;
import view.RegisterView;

import java.sql.Connection;

public class AdminDisplay implements Regulator {
    private final Connection connection;

    public AdminDisplay(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void display(Stage primaryStage) {
        AdminView adminView = new AdminView(primaryStage);

        UserDAO userDAO = new UserDAO(connection);
        ProductDAO productDAO = new ProductDAO(connection);

        new AdminController(productDAO, adminView);
        adminView.show();
    }
}
