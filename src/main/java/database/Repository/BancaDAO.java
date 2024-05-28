package database.Repository;
import Clase.Banca;
import database.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BancaDAO {

    public void createBanca(Banca banca) {
        String sql = "INSERT INTO Banca (nume) VALUES (?)";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, banca.getNume());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    banca.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Banca readBanca(int bancaId) {
        String sql = "SELECT * FROM Banca WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bancaId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Banca banca = new Banca();
                    banca.setId(rs.getInt("id"));
                    banca.setNume(rs.getString("nume"));
                    return banca;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBanca(Banca banca) {
        String sql = "UPDATE Banca SET nume = ? WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, banca.getNume());
            pstmt.setInt(2, banca.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBanca(int bancaId) {
        String sql = "DELETE FROM Banca WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bancaId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
