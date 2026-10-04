package dao;

import config.DatabaseConnection;
import models.TableRestaurant;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TableRestaurantDAO {
    public UUID getTableIdByNumero(int numeroTable) {
        String sql = "SELECT id FROM tables_restaurant WHERE numero_table = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, numeroTable);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return (UUID) rs.getObject("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur de lecture de la table : " + e.getMessage());
        }
        return null;
    }

    public UUID ajouterTableEtRetournerId(TableRestaurant table) {
        String sql = "INSERT INTO tables_restaurant (numero_table, capacite) VALUES (?, ?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, table.getNumeroTable());
            pstmt.setInt(2, table.getCapacite());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return (UUID) rs.getObject("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion de la table : " + e.getMessage());
        }
        return null;
    }

    public List<TableRestaurant> getToutesLesTables() {
        List<TableRestaurant> tables = new ArrayList<>();
        // On sélectionne maintenant la colonne statut_actuel
        String sql = "SELECT id, numero_table, capacite, statut_actuel FROM tables_restaurant ORDER BY numero_table ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                UUID id = (UUID) rs.getObject("id");
                int numero = rs.getInt("numero_table");
                int capacite = rs.getInt("capacite");
                String statut = rs.getString("statut_actuel");
                tables.add(new TableRestaurant(id, numero, capacite, statut));
            }
        } catch (SQLException e) {
            System.err.println(" Erreur : " + e.getMessage());
        }
        return tables;
    }

    public boolean mettreAJourStatut(UUID tableId, String nouveauStatut) {
        String sql = "UPDATE tables_restaurant SET statut_actuel = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nouveauStatut);
            pstmt.setObject(2, tableId);

            int lignesModifiees = pstmt.executeUpdate();
            return lignesModifiees > 0;

        } catch (SQLException e) {
            System.err.println("Erreur de mise à jour : " + e.getMessage());
            return false;
        }
    }
}