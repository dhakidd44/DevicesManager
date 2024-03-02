package com.management;

import java.util.Date;

public class ObjetConnecte {

    private int id;
    private String nom;
    private String type;
    private String categorie;
    private String location; // Ajout de l'attribut location
    private String getAdresse_ip;
    private int etat;
    private Date timestamp;

    public ObjetConnecte(int id, String nom, String type, String categorie, String location, String getAdresse_ip, int etat, Date timestamp) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.categorie = categorie;
        this.location = location;
        this.getAdresse_ip = getAdresse_ip;
        this.etat = etat;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getLocation() {
        return location; // Méthode pour obtenir l'location de l'appareil
    }

    public String getAdresse_ip() {
        return getAdresse_ip;
    }

    public int getEtat() {
        return etat;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ObjetConnecteJava{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", categorie='" + categorie + '\'' +
                ", location='" + location + '\'' +
                ", getAdresse_ip='" + getAdresse_ip + '\'' +
                ", etat=" + etat +
                ", timestamp=" + timestamp +
                '}';
    }
}
