package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "GestorProductesIvanM";
    private static final String USER = "root";
    private static final String PASSWORD = "Ivan1234";

    public static Connection getNewConnection() {
        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC", HOST, PORT, DB_NAME);
        try {
            Connection connection = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Connexió establerta a la base de dades: " + DB_NAME);
            return connection;
        } catch (SQLException e) {
            System.out.println("Error de connexió a " + DB_NAME + ": " + e.getMessage());
            return null;
        }
    }

    // Per connexió sense especificar base de dades
    public static Connection getGeneralConnection() {
        String url = String.format("jdbc:mysql://%s:%s/?useSSL=false&serverTimezone=UTC", HOST, PORT);
        try {
            return DriverManager.getConnection(url, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de connexió general: " + e.getMessage());
            return null;
        }
    }
}
