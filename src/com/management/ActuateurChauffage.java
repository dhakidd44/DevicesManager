package com.management;

public class ActuateurChauffage extends Actuateur {

    private int niveauChauffage;

    public ActuateurChauffage(int idActuateur, String nom, String modele, String numeroSerie, int etat) {
        super(idActuateur, nom, modele, numeroSerie, etat);
    }

    public int getNiveauChauffage() {
        return niveauChauffage;
    }

    public void setNiveauChauffage(int niveauChauffage) {
        this.niveauChauffage = niveauChauffage;
    }

    @Override
    public String toString() {
        return "Actuateur de Chauffage - Nom: " + getNom() + ", Modèle: " + getModele() +
                ", Numéro de Série: " + getNumeroSerie() + ", État: " + getEtat() +
                ", Niveau de Chauffage: " + niveauChauffage;
    }
}
