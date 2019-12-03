package allie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static String URL = "jdbc:sqlserver://localhost;integratedSecurity=true;";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection error!");
        }
    //    System.out.println("Connected to database.");
        return connection;
    }

    public static void disconnect(Connection connection) {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Disconnect error!");
        }
    //    System.out.println("Disconnected from database.");
    }


}
