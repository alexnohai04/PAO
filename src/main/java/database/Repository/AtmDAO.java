package database.Repository;
import Clase.Atm;
import database.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtmDAO {

    public void createAtm(Atm atm) {
        String sql = "INSERT INTO Atm (strada, numar) VALUES (?, ?)";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, atm.getStrada());
            pstmt.setInt(2, atm.getNumar());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    atm.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Atm readAtm(int atmId) {
        String sql = "SELECT * FROM Atm WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, atmId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Atm atm = new Atm();
                    atm.setId(rs.getInt("id"));
                    atm.setStrada(rs.getString("strada"));
                    atm.setNumar(rs.getInt("numar"));
                    return atm;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAtm(Atm atm) {
        String sql = "UPDATE Atm SET strada = ?, numar = ? WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, atm.getStrada());
            pstmt.setInt(2, atm.getNumar());
            pstmt.setInt(3, atm.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAtm(int atmId) {
        String sql = "DELETE FROM Atm WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, atmId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
