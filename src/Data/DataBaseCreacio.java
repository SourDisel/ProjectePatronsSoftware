package Data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreacio {

    public static void crearbasedades() {
        String sql = "USE GestorProductesIvanM;"
                + "CREATE TABLE IF NOT EXISTS producte ("
                + "codi INT AUTO_INCREMENT PRIMARY KEY,"
                + "nom VARCHAR(255) NOT NULL CHECK (LENGTH(nom) >= 2),"
                + "categoria INT NOT NULL,"
                + "preu DECIMAL(10, 2) NOT NULL CHECK (preu > 0),"
                + "tipus_preu VARCHAR(20) NOT NULL CHECK (tipus_preu IN ('unitari', 'pes', 'paquet')),"
                + "stock INT NOT NULL CHECK (stock >= 0),"
                + "oferta BOOLEAN NOT NULL,"
                + "FOREIGN KEY (categoria) REFERENCES categoria(id)"
                + ");";

        try (Connection conn = DataBaseConnection.getNewConnection();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crearCategoria() {
        String sql = "CREATE DATABASE IF NOT EXISTS GestorProductesIvanM;"
                + "USE GestorProductesIvanM;"
                + "CREATE TABLE IF NOT EXISTS categoria ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nom VARCHAR(255) NOT NULL UNIQUE CHECK (LENGTH(nom) >= 2),"
                + "descripcio VARCHAR(200)"
                + ");";

        try (Connection conn = DataBaseConnection.getNewConnection();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void CreacionBase() {
        crearCategoria();
        crearbasedades();
    }
}
