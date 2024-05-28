//package database.DAO;
//
//import Clase.ContBancar;
//import database.DatabaseConfiguration;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ContBancarDAO {
//
//    public void createContBancar(ContBancar contBancar) {
//        String sql = "INSERT INTO ContBancar (nume, IBAN, sold, clientId) VALUES (?, ?, ?, ?)";
//        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
//            pstmt.setString(1, contBancar.getNume());
//            pstmt.setString(2, contBancar.getIBAN());
//            pstmt.setInt(3, contBancar.getSold());
//            pstmt.setInt(4, contBancar.getClientId());
//            pstmt.executeUpdate();
//            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    contBancar.setId(generatedKeys.getInt(1));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ContBancar readContBancar(int contBancarId) {
//        String sql = "SELECT * FROM ContBancar WHERE id = ?";
//        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, contBancarId);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    ContBancar contBancar = new ContBancar();
//                    contBancar.setId(rs.getInt("id"));
//                    contBancar.setNume(rs.getString("nume"));
//                    contBancar.setIBAN(rs.getString("IBAN"));
//                    contBancar.setSold(rs.getInt("sold"));
//                    contBancar.setClientId(rs.getInt("clientId"));
//                    return contBancar;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void updateContBancar(ContBancar contBancar) {
//        String sql = "UPDATE ContBancar SET nume = ?, IBAN = ?, sold = ?, clientId = ? WHERE id = ?";
//        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, contBancar.getNume());
//            pstmt.setString(2, contBancar.getIBAN());
//            pstmt.setInt(3, contBancar.getSold());
//            pstmt.setInt(4, contBancar.getClientId());
//            pstmt.setInt(5, contBancar.getId());
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteContBancar(int contBancarId) {
//        String sql = "DELETE FROM ContBancar WHERE id = ?";
//        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, contBancarId);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<ContBancar> getConturiByClientId(int clientId) {
//        String sql = "SELECT * FROM ContBancar WHERE clientId = ?";
//        List<ContBancar> conturi = new ArrayList<>();
//        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, clientId);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                while (rs.next()) {
//                    ContBancar contBancar = new ContBancar();
//                    contBancar.setId(rs.getInt("id"));
//                    contBancar.setNume(rs.getString("nume"));
//                    contBancar.setIBAN(rs.getString("IBAN"));
//                    contBancar.setSold(rs.getInt("sold"));
//                    contBancar.setClientId(rs.getInt("clientId"));
//                    conturi.add(contBancar);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conturi;
//    }
//}
