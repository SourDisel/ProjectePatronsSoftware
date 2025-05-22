package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "GestorProductesIvanM";
    private static final String URL = String.format("jdbc:mysql://%s:%s/%s", HOST, PORT, DB_NAME);
    private static final String USER = "root";
    private static final String PASSWORD = "Ivan1234";

    public static Connection getNewConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexió temporal establerta.");
            return connection;
        } catch (SQLException e) {
            System.out.println("Error de connexió temporal: " + e.getMessage());
            return null;
        }
    }

}
