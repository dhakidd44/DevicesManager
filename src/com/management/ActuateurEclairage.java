package com.management;
public class ActuateurEclairage extends Actuateur {

    private boolean allume;

    public ActuateurEclairage(int idActuateur, String nom, String modele, String numeroSerie, int etat) {
        super(idActuateur, nom, modele, numeroSerie, etat);
    }

    public boolean isAllume() {
        return allume;
    }

    public void setAllume(boolean allume) {
        this.allume = allume;
    }

    @Override
    public String toString() {
        return "Actuateur d'Éclairage - Nom: " + getNom() + ", Modèle: " + getModele() +
                ", Numéro de Série: " + getNumeroSerie() + ", État: " + getEtat() +
                ", Éclairage Allumé: " + allume;
    }
}
