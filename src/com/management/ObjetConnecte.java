package com.management;

// Définition de la classe ObjetConnecte
public class ObjetConnecte {

    // Attributs de la classe
    private int id; 
    private String nom; 
    private String type; 
    private String categorie;
    private String location;
    private String adresse_ip;
    private int etat; // État de l'appareil (1 pour actif, 0 pour inactif)

    // Constructeur avec les paramètres
    public ObjetConnecte(int id, String nom, String type, String categorie, String location,String adresse_ip, int etat) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.categorie = categorie;
        this.location = location;
        this.adresse_ip = adresse_ip ;
        this.etat = etat;
    }

    public ObjetConnecte(int id) {
        this.id = id;
    }

    // Méthode pour obtenir l'identifiant de l'appareil
    public int getId() {
        return id;
    }

    // Méthode pour obtenir le nom de l'appareil
    public String getNom() {
        return nom;
    }
    public String getadresse_ip() {
        return adresse_ip;
    }

    // Méthode pour obtenir le type de l'appareil
    public String getType() {
        return type;
    }

    // Méthode pour obtenir la catégorie de l'appareil
    public String getCategorie() {
        return categorie;
    }

    // Méthode pour obtenir l'location de l'appareil
    public String getAdresse() {
        return location;
    }
    // Méthode pour définir l'état de l'appareil
    public void setEtat(int etat) {
        this.etat = etat;
    }

    // Méthode toString() pour faciliter l'affichage
    @Override
    public String toString() {
        return "Appareil{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", categorie='" + categorie + '\'' +
                ", location='" + location + '\'' +
                ", adresseIp='" + adresse_ip + '\'' +
                ", etat=" + etat +
                '}';
    }
}
