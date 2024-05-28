package database.Repository;

import Clase.PremiumPlan;
import database.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PremiumPlanDAO {

    public void createPremiumPlan(PremiumPlan premiumPlan) {
        String sql = "INSERT INTO PremiumPlan (pret, startDate) VALUES (?, ?)";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, premiumPlan.getPret());
            pstmt.setDate(2, java.sql.Date.valueOf(premiumPlan.getStartDate()));
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    premiumPlan.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PremiumPlan readPremiumPlan(int premiumPlanId) {
        String sql = "SELECT * FROM PremiumPlan WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, premiumPlanId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    PremiumPlan premiumPlan = new PremiumPlan();
                    premiumPlan.setId(rs.getInt("id"));
                    premiumPlan.setPret(rs.getInt("pret"));
                    premiumPlan.setStartDate(rs.getDate("startDate").toLocalDate());
                    return premiumPlan;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePremiumPlan(PremiumPlan premiumPlan) {
        String sql = "UPDATE PremiumPlan SET pret = ?, startDate = ? WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, premiumPlan.getPret());
            pstmt.setDate(2, java.sql.Date.valueOf(premiumPlan.getStartDate()));
            pstmt.setInt(3, premiumPlan.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePremiumPlan(int premiumPlanId) {
        String sql = "DELETE FROM PremiumPlan WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, premiumPlanId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

