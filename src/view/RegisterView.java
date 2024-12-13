package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {
    private Stage stage;
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField emailField;
    private TextField roleField;
    private Button registerButton;

    public RegisterView(Stage stage) {
        this.stage = stage;

        // Initialize UI components
        usernameField = new TextField();
        passwordField = new PasswordField();
        emailField = new TextField();
        roleField = new TextField();
        registerButton = new Button("Register");

        // Set prompt text for fields
        usernameField.setPromptText("Enter Username");
        passwordField.setPromptText("Enter Password");
        emailField.setPromptText("Enter Email");

        // Layout
        VBox layout = new VBox(10,
                new Label("Username:"), usernameField,
                new Label("Password:"), passwordField,
                new Label("Email:"), emailField,
                registerButton
        );
        layout.setStyle("-fx-padding: 20;");

        // Create and set the scene
        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
    }

    // Listener for the Register button
    public void setRegisterListener(RegisterListener listener) {
        registerButton.setOnAction(e ->
                listener.onRegister(usernameField.getText(), passwordField.getText(), emailField.getText(), Boolean.parseBoolean(roleField.getText()))
        );
    }

    public void show() {
        stage.show();
    }
    // Show message in an alert
    public void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }

    // Interface for handling register events
    public interface RegisterListener {
        void onRegister(String username, String password, String email, boolean isAdmin);
    }
}
