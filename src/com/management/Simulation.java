package com.management;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.sql.Timestamp;

public class Simulation {
// Méthode pour insérer des données aléatoires dans la table "Capteurs"
public void insertRandomCapteurs(Connection connection) throws SQLException {
    Random random = new Random();
    try (Statement statement = connection.createStatement()) {
        // Création de 10 capteurs avec des données aléatoires
        for (int i = 1; i <= 10; i++) {
            String nom = "Capteur" + i;
            String modele = "Modèle" + i;
            String numeroSerie = "Série" + i;
            int etat = random.nextInt(2); // Génère un état aléatoire (0 ou 1)
            int objetID = random.nextInt(5) + 1; // Génère un objet ID aléatoire entre 1 et 5
            
            String insertCapteur = String.format("INSERT INTO Capteurs (Nom, Modele, Numero_serie, Etat, Objet_ID) VALUES ('%s', '%s', '%s', %d, %d)",
                                                    nom, modele, numeroSerie, etat, objetID);
            statement.executeUpdate(insertCapteur);
        }
    }
}

    // Méthode pour insérer des données aléatoires de capteurs dans la table "DonneesCapteurs"
    public void insertRandomCapteursData(Connection connection) throws SQLException {
        Random random = new Random();
        try (Statement statement = connection.createStatement()) {
            // Générer des données pour 10 capteurs
            for (int capteurId = 1; capteurId <= 10; capteurId++) {
                // Générer des données pour 10 mesures par capteur
                for (int i = 1; i <= 10; i++) {
                    float valeur = random.nextFloat() * 100; // Valeur de mesure aléatoire entre 0 et 100
                    Timestamp timestamp = generateRandomTimestamp(); // Générer un timestamp aléatoire
                    
                    // Insérer les données dans la table DonneesCapteurs
                    String insertData = String.format("INSERT INTO DonneesCapteurs (Valeur, Timestamp, Capteur_ID) VALUES (%f, '%s', %d)",
                                                      valeur, timestamp, capteurId);
                    statement.executeUpdate(insertData);
                }
            }
        }
    }
    
    //generer l here automoatiwque
    private Timestamp generateRandomTimestamp() {
        long offset = Timestamp.valueOf("2023-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2024-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long) (Math.random() * diff));
    }
}
