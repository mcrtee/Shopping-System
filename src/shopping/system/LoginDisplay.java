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

public class LoginDisplay implements Regulator {
    private final Connection connection;

    public LoginDisplay(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void display(Stage primaryStage) {
        LoginView loginView = new LoginView(primaryStage);

        UserDAO userDAO = new UserDAO(connection);

        new LoginController(loginView, userDAO);
        loginView.show();
    }
}
