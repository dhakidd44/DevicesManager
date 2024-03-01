package com.management;

public class ActuateurClimatisation extends Actuateur {

    private int niveauClimatisation;

    public ActuateurClimatisation(int idActuateur, String nom, String modele, String numeroSerie, int etat) {
        super(idActuateur, nom, modele, numeroSerie, etat);
    }

    public int getNiveauClimatisation() {
        return niveauClimatisation;
    }

    public void setNiveauClimatisation(int niveauClimatisation) {
        this.niveauClimatisation = niveauClimatisation;
    }

    @Override
    public String toString() {
        return "Actuateur de Climatisation - Nom: " + getNom() + ", Modèle: " + getModele() +
                ", Numéro de Série: " + getNumeroSerie() + ", État: " + getEtat() +
                ", Niveau de Climatisation: " + niveauClimatisation;
    }
}
