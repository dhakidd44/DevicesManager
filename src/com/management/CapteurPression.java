package com.management;

public class CapteurPression extends Capteur {

    private double pression;

    public CapteurPression(int id_Capteur, String nom, String modele, String numeroSerie, int etat) {
        super(id_Capteur, nom, modele, numeroSerie, etat);
    }

    public double getPression() {
        return pression;
    }

    public void setPression(double pression) {
        this.pression = pression;
    }

    @Override
    public String toString() {
        return "Capteur de Pression - Nom: " + getNom() + ", Modèle: " + getModele() +
                ", Numéro de Série: " + getNumeroSerie() + ", État: " + getEtat() +
                ", Pression: " + pression;
    }
}
