import controller.UserController;
import dao.DatabaseConnection;
import dao.UserDAO;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Connection connection = null;

        try {
            // Use DatabaseConnection to establish the connection
            connection = DatabaseConnection.connect();

            // Create a UserController instance
            UserController userController = new UserController(connection);

            System.out.println("Select an option: ");
            System.out.println("1. Register");
            System.out.println("2. Login");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            if (choice == 1) {
                // Handle user registration
                userController.registerUser();
            } else if (choice == 2) {
                // Handle user login
                User loggedInUser = userController.loginUser();
                if (loggedInUser != null) {
                    // Check if the user is an admin
                    if (loggedInUser.getRole().equals("admin")) {
                        System.out.println("You have admin privileges.");
                        // Admin actions like adding and deleting products can be added here
                    } else {
                        System.out.println("You have user privileges.");
                    }
                }
            } else {
                System.out.println("Invalid choice.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
