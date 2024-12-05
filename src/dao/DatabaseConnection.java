package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:2006/shopping_system";
        String user = "postgres";
        String password = "1234567m";

        return DriverManager.getConnection(url, user, password);
    }
}
