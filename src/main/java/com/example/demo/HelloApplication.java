package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the LoginView as the initial scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            primaryStage.setScene(new Scene(loader.load()));

            // Set the title for the login window
            primaryStage.setTitle("Login - Shopping System");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
