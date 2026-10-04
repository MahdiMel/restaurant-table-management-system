package dao;

import config.DatabaseConnection;
import models.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class ReservationDAO {
    public boolean isTableDisponible(UUID tableId, Timestamp dateHeure) {
        String sql = "SELECT COUNT(*) FROM reservations WHERE table_id = ? AND date_heure = ? AND statut != 'ANNULEE'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, tableId);
            pstmt.setTimestamp(2, dateHeure);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count == 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de la disponibilité : " + e.getMessage());
        }
        return false;
    }

    // 2. Créer la réservation
    public boolean creerReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (client_id, table_id, date_heure, statut) VALUES (?, ?, ?, ?::statut_reservation)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, reservation.getClientId());
            pstmt.setObject(2, reservation.getTableId());
            pstmt.setTimestamp(3, reservation.getDateHeure());
            pstmt.setString(4, reservation.getStatut());

            int lignesAffectees = pstmt.executeUpdate();
            if (lignesAffectees > 0) {
                System.out.println("Réservation créée avec succès pour la date : " + reservation.getDateHeure());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de la réservation : " + e.getMessage());
        }
        return false;
    }
}