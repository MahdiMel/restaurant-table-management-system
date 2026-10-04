package models;

import java.util.UUID;

public class Client {
    private UUID id;
    private String nom;
    private String telephone;

    public Client(UUID id, String nom, String telephone) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
    }

    public Client(String nom, String telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom='" + nom + '\'' + ", telephone='" + telephone + '\'' + '}';
    }
}