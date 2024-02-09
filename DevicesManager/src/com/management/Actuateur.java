/*
  Nom du fichier: Actuateur.java
  Titre      : Gestion des actionneurs.
  Auteur     : Ernest Samuel Andre
  Date       : 06/02/2024
  Description: Cette classe définit les actionneurs qui sont utilisés pour effectuer des actions en réponse à des conditions détectées par des capteurs.
               Elle inclut des attributs pour représenter les caractéristiques de l'actionneur, ainsi que des méthodes pour accéder et modifier ces attributs.
               Les actionneurs sont généralement utilisés dans des systèmes d'automatisation et de contrôle.
  Version    : 1.0.0
*/

package com.management;

public class Actuateur extends ObjetConnecte {

    // Attributs de la classe
    private int idActuateur; // Renommé pour éviter le conflit de noms
    private String nom;
    private String modele;
    private String numeroSerie;
    private int etat;

    // Constructeur avec les paramètres
    public Actuateur(int id_Actuateur, String nom, String modele, String numeroSerie, int etat) {
        super(id_Actuateur); // Appeler le constructeur de la classe parente avec l'id_Actuateur
        this.idActuateur = id_Actuateur;
        this.nom = nom;
        this.modele = modele;
        this.numeroSerie = numeroSerie;
        this.etat = etat;
    }

    // Getters et Setters
    // Méthodes pour accéder et modifier les attributs (nom, modele, numeroSerie,
    // etat)
    public int getIdActuateur() {
        return idActuateur;
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
