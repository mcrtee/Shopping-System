package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:2006/shopping_system";  // Update with your DB name
        String user = "postgres";  // Change to your DB user
        String password = "1234567m";  // Change to your DB password

        return DriverManager.getConnection(url, user, password);
    }
}
