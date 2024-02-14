/*
  Nom du fichier: Capteur.java
  Titre      : Gestion des données du capteur.
  Auteur     : Ernest Samuel Andre
  Date       : 18/01/2024
  Description: Cette classe définit les données capturées par un capteur, telles que la température,
               l'humidité, la pression, la détection de mouvement, l'intensité lumineuse, le niveau sonore, etc.
               Elle inclut des méthodes pour accéder et modifier ces données ainsi qu'une méthode toString() pour l'affichage.
  Version    : 1.0.0
*/

package com.management;

public class Capteur extends ObjetConnecte {

    // Attributs de la classe
    private int idCapteur; // Renommé pour éviter le conflit de noms
    private String nom;
    private String modele;
    private String numeroSerie;
    private int etat;

    // Constructeur avec les paramètres
    public Capteur(int id_Capteur, String nom, String modele, String numeroSerie, int etat) {
        super(id_Capteur); // Appeler le constructeur de la classe parente avec l'id_Capteur
        this.idCapteur = id_Capteur;
        this.nom = nom;
        this.modele = modele;
        this.numeroSerie = numeroSerie;
        this.etat = etat;
    }

    // Getters et Setters
    // Méthodes pour accéder et modifier les attributs (nom, modele, numeroSerie,
    // etat)
    public int getIdCapteur() {
        return idCapteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
