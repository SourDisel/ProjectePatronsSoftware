package Data;
import Model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCategoria {

    public void insertarCategoria(Categoria c) {
        String sql = "INSERT INTO categoria (nom, descripcio) VALUES (?, ?)";
        try (Connection conn = DataBaseConnection.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getDescripcion());

            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                c.setId(generatedKeys.getInt(1));
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Categoria buscarCategoria (int id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";
        try (Connection conn = DataBaseConnection.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Categoria(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("descripcio"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Categoria> llistarCategoria () {
        List<Categoria> llista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try (Connection conn = DataBaseConnection.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("descripcio"));
                llista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return llista;
    }

    public void modificarCategoria (Categoria c) {
        String sql = "UPDATE categoria SET nom = ?, descripcio = ? WHERE id = ?";

        try (Connection conn = DataBaseConnection.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getDescripcion());
            stmt.setInt(3, c.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCategoria (int id) {
        String sql = "DELETE FROM categoria WHERE id = ?";

        try (Connection conn = DataBaseConnection.getNewConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}