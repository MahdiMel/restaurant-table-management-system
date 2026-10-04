package models;

import java.util.UUID;
import java.sql.Timestamp; // On utilise Timestamp pour gérer l'heure exacte (avec fuseau) du rendez-vous

public class Reservation {
    private UUID id;
    private UUID clientId;
    private UUID tableId;
    private Timestamp dateHeure;
    private String statut;

    public Reservation(UUID id, UUID clientId, UUID tableId, Timestamp dateHeure, String statut) {
        this.id = id;
        this.clientId = clientId;
        this.tableId = tableId;
        this.dateHeure = dateHeure;
        this.statut = statut;
    }

    public Reservation(UUID clientId, UUID tableId, Timestamp dateHeure, String statut) {
        this.clientId = clientId;
        this.tableId = tableId;
        this.dateHeure = dateHeure;
        this.statut = statut;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getClientId() { return clientId; }
    public void setClientId(UUID clientId) { this.clientId = clientId; }

    public UUID getTableId() { return tableId; }
    public void setTableId(UUID tableId) { this.tableId = tableId; }

    public Timestamp getDateHeure() { return dateHeure; }
    public void setDateHeure(Timestamp dateHeure) { this.dateHeure = dateHeure; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    @Override
    public String toString() {
        return "Reservation{" + "date=" + dateHeure + ", statut='" + statut + '\'' + '}';
    }
}