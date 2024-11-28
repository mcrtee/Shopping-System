package controller;
import dao.UserDAO;
import java.util.Scanner;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;

public class UserController {
    private UserDAO userDAO;

    public UserController(Connection connection) {
        this.userDAO = new UserDAO(connection); // Initialize the UserDAO with the provided connection
    }

    // Method to handle user registration
    public void registerUser() {
        Scanner scanner = new Scanner(System.in);

        // Get user details
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        // Check if the user is the admin (email matches admin's email)
        String role = email.equals("admin@gmail.com") ? "admin" : "user";

        // Create User object with the determined role
        User newUser = new User(0, name, email, password, role);

        try {
            boolean isRegistered = userDAO.registerUser(newUser);
            if (isRegistered) {
                System.out.println("User registered successfully!");
            } else {
                System.out.println("User registration failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to handle user login
    public User loginUser() {
        Scanner scanner = new Scanner(System.in);

        // Get user credentials
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        try {
            User loggedInUser = userDAO.loginUser(email, password);
            if (loggedInUser != null) {
                System.out.println("Login successful! Welcome, " + loggedInUser.getName());
                return loggedInUser;  // Return the logged-in user object
            } else {
                System.out.println("Invalid credentials.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if login failed
    }

}
