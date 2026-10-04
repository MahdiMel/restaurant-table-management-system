package models;

import java.util.UUID;

public class TableRestaurant {
    private UUID id;
    private int numeroTable;
    private int capacite;
    private String statutActuel;

    public TableRestaurant(UUID id, int numeroTable, int capacite, String statutActuel) {
        this.id = id;
        this.numeroTable = numeroTable;
        this.capacite = capacite;
        this.statutActuel = statutActuel;
    }

    public String getStatutActuel() { return statutActuel; }
    public void setStatutActuel(String statutActuel) { this.statutActuel = statutActuel; }

    public TableRestaurant(int numeroTable, int capacite) {
        this.numeroTable = numeroTable;
        this.capacite = capacite;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public int getNumeroTable() { return numeroTable; }
    public void setNumeroTable(int numeroTable) { this.numeroTable = numeroTable; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    @Override
    public String toString() {
        return "TableRestaurant{" + "numero=" + numeroTable + ", capacite=" + capacite + " places}";
    }
}