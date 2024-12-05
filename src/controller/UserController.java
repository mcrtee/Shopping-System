package controller;
import dao.UserDAO;
import java.util.Scanner;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;

public class UserController {
//    private UserDAO userDAO;
//    private final Scanner scanner = new Scanner(System.in);
//
//    public UserController(Connection connection) {
//        this.userDAO = new UserDAO(connection); // Initialize the UserDAO with the provided connection
//    }
//
//    // Register a user (with hashed password)
//    public void registerUser() {
//        try {
//            System.out.println("Enter your name: ");
//            String name = scanner.nextLine();
//
//            System.out.println("Enter your email: ");
//            String email = scanner.nextLine();
//
//            System.out.println("Enter your password: ");
//            String password = scanner.nextLine();
//
//            // Set default role as "user"
//            String role = "user";
//
//            // Create User object and register it
//            User newUser = new User(username, email, password, role);
//            boolean isRegistered = userDAO.registerUser(newUser);
//
//            if (isRegistered) {
//                System.out.println("Registration successful!");
//
//                // Now, immediately log the user in
//                User loggedInUser = userDAO.loginUser(email, password);
//                if (loggedInUser != null) {
//                    System.out.println("Login successful!");
//                    handleLogin(loggedInUser);  // You can now handle actions based on login
//                } else {
//                    System.out.println("Login failed.");
//                }
//            } else {
//                System.out.println("Registration failed.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    // Method to handle user login
//    public User loginUser() {
//        Scanner scanner = new Scanner(System.in);
//
//        // Get user credentials
//        System.out.println("Enter your email: ");
//        String email = scanner.nextLine();
//        System.out.println("Enter your password: ");
//        String password = scanner.nextLine();
//
//        try {
//            User loggedInUser = userDAO.loginUser(email, password);
//            if (loggedInUser != null) {
//                System.out.println("Login successful! Welcome, " + loggedInUser.getName());
//                return loggedInUser;  // Return the logged-in user object
//            } else {
//                System.out.println("Invalid credentials.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;  // Return null if login failed
//    }
//    // Method to handle actions after login based on user role
//    public void handleLogin(User user) {
//        if (user.getRole().equals("admin")) {
//            handleAdminActions();
//        } else {
//            handleUserActions();
//        }
//    }
//
//    // Method to display admin-specific actions
//    private void handleAdminActions() {
//        while (true) {
//            System.out.println("Welcome Admin! Choose an option:");
//            System.out.println("1. Add Product");
//            System.out.println("2. Delete Product");
//            System.out.println("3. View All Products");
//            System.out.println("4. Logout");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();  // Consume newline character
//
//            switch (choice) {
//                case 1:
//                    // Logic for adding a product
//                    System.out.println("Adding a product...");
//                    // Add implementation here
//                    break;
//                case 2:
//                    // Logic for deleting a product
//                    System.out.println("Deleting a product...");
//                    // Add implementation here
//                    break;
//                case 3:
//                    // Logic to view all products
//                    System.out.println("Displaying all products...");
//                    // Add implementation here
//                    break;
//                case 4:
//                    System.out.println("Logging out...");
//                    return;  // Exit the loop and return to the main menu
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }
//
//    // Method to display user-specific actions
//    private void handleUserActions() {
//        while (true) {
//            System.out.println("Welcome User! Choose an option:");
//            System.out.println("1. View Products");
//            System.out.println("2. Logout");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();  // Consume newline character
//
//            switch (choice) {
//                case 1:
//                    // Logic to view products
//                    System.out.println("Displaying products...");
//                    // Add implementation here
//                    break;
//                case 2:
//                    System.out.println("Logging out...");
//                    return;  // Exit the loop and return to the main menu
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }
}