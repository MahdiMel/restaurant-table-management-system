package dao;

import config.DatabaseConnection;
import models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;       // Ajout de cet import
import java.sql.SQLException;
import java.util.UUID;           // Ajout de cet import

public class ClientDAO {
    public UUID ajouterClientEtRetournerId(Client client) {
        String sql = "INSERT INTO clients (nom, telephone) VALUES (?, ?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getTelephone());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Client inséré/récupéré avec succès !");
                    return (UUID) rs.getObject("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion du client : " + e.getMessage());
        }
        return null;
    }
}