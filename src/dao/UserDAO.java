package dao;

import model.User;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Register a new user with a default role
    public boolean registerUser(User user) throws SQLException {
        String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword())); // Hash the password
            statement.setString(4, user.getRole()); // Default role is "user"
            return statement.executeUpdate() > 0;
        }
    }

    // Login user (check role along with credentials)
    public User loginUser(String email, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedHashedPassword = resultSet.getString("password");

                // Check if the entered password matches the hashed password in the database
                if (BCrypt.checkpw(password, storedHashedPassword)) {
                    // Password matches, return the user object
                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),  // hashed password is returned
                            resultSet.getString("role")
                    );
                }
            }
        }
        return null;  // Invalid credentials
    }

    // Helper method to hash the password
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    // Helper method to check password
    private boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
