package com.management;

// Définition de la classe ObjetConnecte
public class ObjetConnecte {

    // Attributs de la classe
    private int id; // Identifiant de l'appareil
    private String nom; // Nom de l'appareil
    private String type; // Type de l'appareil
    private String categorie; // Catégorie de l'appareil
    private String location;
    private int etat; // État de l'appareil (1 pour actif, 0 pour inactif)

    // Constructeur avec les paramètres
    public ObjetConnecte(int id, String nom, String type, String categorie, String location, int etat) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.categorie = categorie;
        this.location = location;
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
                ", etat=" + etat +
                '}';
    }
}
