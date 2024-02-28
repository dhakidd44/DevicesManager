package com.management;

public class CapteurLuminosite extends Capteur {

    private double luminosite;

    public CapteurLuminosite(int id_Capteur, String nom, String modele, String numeroSerie, int etat) {
        super(id_Capteur, nom, modele, numeroSerie, etat);
    }

    public double getLuminosite() {
        return luminosite;
    }

    public void setLuminosite(double luminosite) {
        this.luminosite = luminosite;
    }

    @Override
    public String toString() {
        return "Capteur de Luminosité - Nom: " + getNom() + ", Modèle: " + getModele() +
                ", Numéro de Série: " + getNumeroSerie() + ", État: " + getEtat() +
                ", Luminosité: " + luminosite;
    }
}
