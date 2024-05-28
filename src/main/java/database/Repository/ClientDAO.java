package database.Repository;

import Clase.Client;
import database.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public void createClient(Client client) {
        String sql = "INSERT INTO Client (nume, nrTelefon) VALUES (?, ?)";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, client.getNume());
            pstmt.setString(2, client.getNrTelefon());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client readClient(int clientId) {
        String sql = "SELECT * FROM Client WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setNume(rs.getString("nume"));
                    client.setNrTelefon(rs.getString("nrTelefon"));
                    return client;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateClient(Client client) {
        String sql = "UPDATE Client SET nume = ?, nrTelefon = ? WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.getNume());
            pstmt.setString(2, client.getNrTelefon());
            pstmt.setInt(3, client.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int clientId) {
        String sql = "DELETE FROM Client WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getAllClients() {
        String sql = "SELECT * FROM Client";
        List<Client> clients = new ArrayList<>();
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setNume(rs.getString("nume"));
                client.setNrTelefon(rs.getString("nrTelefon"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}

