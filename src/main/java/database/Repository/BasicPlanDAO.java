package database.Repository;

import Clase.BasicPlan;
import database.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasicPlanDAO {

    public void createBasicPlan(BasicPlan basicPlan) {
        String sql = "INSERT INTO BasicPlan (pret, startDate) VALUES (?, ?)";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, basicPlan.getPret());
            pstmt.setDate(2, java.sql.Date.valueOf(basicPlan.getStartDate()));
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    basicPlan.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BasicPlan readBasicPlan(int basicPlanId) {
        String sql = "SELECT * FROM BasicPlan WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, basicPlanId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    BasicPlan basicPlan = new BasicPlan();
                    basicPlan.setId(rs.getInt("id"));
                    basicPlan.setPret(rs.getInt("pret"));
                    basicPlan.setStartDate(rs.getDate("startDate").toLocalDate());
                    return basicPlan;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBasicPlan(BasicPlan basicPlan) {
        String sql = "UPDATE BasicPlan SET pret = ?, startDate = ? WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, basicPlan.getPret());
            pstmt.setDate(2, java.sql.Date.valueOf(basicPlan.getStartDate()));
            pstmt.setInt(3, basicPlan.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBasicPlan(int basicPlanId) {
        String sql = "DELETE FROM BasicPlan WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, basicPlanId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

