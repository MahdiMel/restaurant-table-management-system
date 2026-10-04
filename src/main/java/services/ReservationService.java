//A VENIR POUR V2

package services;

import dao.ReservationDAO;
import models.Reservation;

import java.sql.Timestamp;
import java.util.UUID;

public class ReservationService {

    private ReservationDAO reservationDAO;

    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
    }

    public boolean traiterDemandeReservation(UUID clientId, UUID tableId, Timestamp dateHeure) {

        System.out.println("⏳ Vérification de la disponibilité de la table...");

        if (reservationDAO.isTableDisponible(tableId, dateHeure)) {
            System.out.println("Table libre. Enregistrement de la réservation...");

            // On crée l'objet avec un statut par défaut
            Reservation nouvelleReservation = new Reservation(clientId, tableId, dateHeure, "EN_ATTENTE");

            boolean succes = reservationDAO.creerReservation(nouvelleReservation);

            if (succes) {
                System.out.println("[À VENIR]");
                return true;
            }
        } else {
            System.out.println("Désolé, cette table est déjà réservée pour ce créneau.");
        }
        return false;
    }
}