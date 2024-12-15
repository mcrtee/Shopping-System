package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationController {

    @FXML
    private CheckBox adminCheckBox;

    private final UserDAO userDAO;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button cancelButton;

    public RegistrationController() throws SQLException {
        // Initialize the UserDAO with a database connection
        this.userDAO = new UserDAO(DatabaseConnection.getConnection());
    }

    // Handle the registration process
    @FXML
    public void handleRegister() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validate if any fields are empty
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() ) {
            showAlert("Incomplete fields", "Please fill in all fields.", Alert.AlertType.ERROR);
            return;
        }


        // Check if the user already exists
        if (userDAO.getUserByUsername(username) != null) {
            showAlert("Username taken", "This username is already taken. Please choose another one.", Alert.AlertType.ERROR);
            return;
        }


        boolean isAdmin = adminCheckBox.isSelected(); // Use the admin checkbox value

        // Register the user
        boolean success = userDAO.registerUser(username, email, password, isAdmin);

        if (success) {
            showAlert("Registration successful", "User has been successfully registered.", Alert.AlertType.INFORMATION);
            handleCancel();  // Redirect to login after success
        } else {
            showAlert("Registration failed", "Something went wrong. Please try again.", Alert.AlertType.ERROR);
        }
    }

    // Handle cancel action (e.g., redirect to login view)
    @FXML
    public void handleCancel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            LoginController loginController = loader.getController();
            loginController.handleLogin();
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Show alert with a message
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
