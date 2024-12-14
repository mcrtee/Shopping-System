package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // For simplicity, we are checking if username and password are not empty
        // In a real application, you would validate against a database or an authentication service
        if (!username.isEmpty() && !password.isEmpty()) {
            System.out.println("Login successful!");
            // Load the MainView after successful login
            loadMainView();
        } else {
            System.out.println("Please enter both username and password.");
        }
    }

    private void loadMainView() {
        try {
            // Load the main view FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            // Set the scene to the main view
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            // Optionally, you can pass data (e.g., user info) to the MainController here if needed

            stage.setTitle("Shopping System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading MainView.");
        }
    }
}
