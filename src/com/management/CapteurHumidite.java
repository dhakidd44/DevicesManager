package com.management;

public class CapteurHumidite extends Capteur {

    private double humidite;

    public CapteurHumidite(int id_Capteur, String nom, String modele, String numeroSerie, int etat) {
        super(id_Capteur, nom, modele, numeroSerie, etat);
    }

    public double getHumidite() {
        return humidite;
    }

    public void setHumidite(double humidite) {
        this.humidite = humidite;
    }

    @Override
    public String toString() {
        return "Capteur d'Humidité - Nom: " + getNom() + ", Modèle: " + getModele() +
                ", Numéro de Série: " + getNumeroSerie() + ", État: " + getEtat() +
                ", Humidité: " + humidite;
    }
}
