package com.management;

public class CapteurSonore extends Capteur {

    private double niveauSon;

    public CapteurSonore(int id_Capteur, String nom, String modele, String numeroSerie, int etat) {
        super(id_Capteur, nom, modele, numeroSerie, etat);
    }

    public double getNiveauSon() {
        return niveauSon;
    }

    public void setNiveauSon(double niveauSon) {
        this.niveauSon = niveauSon;
    }

    @Override
    public String toString() {
        return "Capteur Sonore - Nom: " + getNom() + ", Modèle: " + getModele() +
                ", Numéro de Série: " + getNumeroSerie() + ", État: " + getEtat() +
                ", Niveau Sonore: " + niveauSon;
    }
}
