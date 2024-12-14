package com.example.demo;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    public boolean registerUser(String username, String email, String password, boolean isAdmin) {
        try {
            String query = "INSERT INTO users (username, password, email, isAdmin) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setBoolean(4, isAdmin);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;  // Returns true if a row is successfully inserted
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Login user (check role along with credentials)
    public boolean loginUser(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();  // Returns true if user is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Helper method to hash the password
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    // Helper method to check password
    boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public User getUserByUsername(String username) {
        User user = null; // Initialize user as null in case no match is found
        try {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getBoolean("isAdmin")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}