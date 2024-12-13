package shopping.system;

import controller.LoginController;
import controller.ProductController;
import dao.ProductDAO;
import dao.UserDAO;
import javafx.stage.Stage;
import view.LoginView;
import view.RegisterView;
import view.ShoppingView;

import java.sql.Connection;

public class UserDisplay implements Regulator {
    private final Connection connection;

    public UserDisplay(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void display(Stage primaryStage) {
        ShoppingView shoppingView = new ShoppingView(primaryStage);

        UserDAO userDAO = new UserDAO(connection);
        ProductDAO productDAO = new ProductDAO(connection);
        new ProductController(productDAO, shoppingView);
        shoppingView.show();
    }
}
