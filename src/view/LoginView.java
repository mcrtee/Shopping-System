package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private Stage stage;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public LoginView(Stage stage) {
        this.stage = stage;
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");

        VBox layout = new VBox(10, new Label("Username:"), usernameField, new Label("Password:"), passwordField, loginButton);
        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
    }

    public void setLoginListener(LoginListener listener) {
        loginButton.setOnAction(e -> listener.onLogin(usernameField.getText(), passwordField.getText()));
    }

    public void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }

    public void close() {
    }

    public interface LoginListener {
        void onLogin(String username, String password);
    }
}
