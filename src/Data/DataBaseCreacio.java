package Data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreacio {

    public static void crearCategoria() {
        // Crear la base de dades (connexió general)
        try (Connection conn = DataBaseConnection.getGeneralConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS GestorProductesIvanM");

        } catch (SQLException e) {
            System.out.println("Error creant la base de dades: " + e.getMessage());
            return;
        }

        // Crear taula categoria
        try (Connection conn = DataBaseConnection.getNewConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS categoria (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                         "nom VARCHAR(255) NOT NULL UNIQUE," +
                         "descripcio VARCHAR(200)" +
                         ")";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Error creant la taula categoria: " + e.getMessage());
        }
    }

    public static void crearProducte() {
        try (Connection conn = DataBaseConnection.getNewConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS producte (" +
                         "codi INT AUTO_INCREMENT PRIMARY KEY," +
                         "nom VARCHAR(255) NOT NULL," +
                         "categoria INT NOT NULL," +
                         "preu DECIMAL(10, 2) NOT NULL," +
                         "tipus_preu VARCHAR(20) NOT NULL," +
                         "stock INT NOT NULL," +
                         "oferta BOOLEAN NOT NULL," +
                         "FOREIGN KEY (categoria) REFERENCES categoria(id)" +
                         ")";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Error creant la taula producte: " + e.getMessage());
        }
    }

    public static void CreacionBase() {
        crearCategoria();
        crearProducte();
    }
}
