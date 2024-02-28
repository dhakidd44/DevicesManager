package com.management;

public class CapteurTemperature extends Capteur {

    private double temperature;

    public CapteurTemperature(int id_Capteur, String nom, String modele, String numeroSerie, int etat) {
        super(id_Capteur, nom, modele, numeroSerie, etat);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Capteur de Température - Nom: " + getNom() + ", Modèle: " + getModele() +
                ", Numéro de Série: " + getNumeroSerie() + ", État: " + getEtat() +
                ", Température: " + temperature;
    }
}
