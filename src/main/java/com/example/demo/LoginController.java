package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginController {

    private UserDAO userDAO;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    private Connection connection;

    public LoginController() throws SQLException {
        this.userDAO = new UserDAO(DatabaseConnection.getConnection());

    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (userDAO.loginUser(username, password)) {
            User user = userDAO.getUserByUsername(username);

            if (user.isAdmin()) {
               loadAdminView(user);
            } else {
                loadShoppingView(user);
            }
        }
    }

    private void loadShoppingView(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShoppingView.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            // Pass the user object to ProductController
            ProductController productController = loader.getController();
            productController.initializeWithUser(user);

            stage.setTitle("Shopping System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load shopping view.", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAdminView(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            // Pass the user object to AdminController
            AdminController adminController = loader.getController();
            adminController.initializeWithUser(user);

            stage.setTitle("Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load admin view.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
