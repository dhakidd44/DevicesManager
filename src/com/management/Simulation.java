package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.sql.Timestamp;

public class Simulation {

    /// Méthode pour insérer des données aléatoires dans la table "Objets"
    public void insertRandomObjets(Connection connection) throws SQLException {
        Random random = new Random();
        try (Statement statement = connection.createStatement()) {
            // Création de 10 objets avec des données aléatoires
            for (int i = 1; i <= 10; i++) {
                String nom = "Objet" + i;
                String type = getRandomType();
                String categorie = getRandomCategorie();
                String location = getRandomLocation();
                String adresseIp = getRandomIpAddress();
                int etat = random.nextInt(2); // Génère un état aléatoire (0 ou 1)
                String insertObjet = String.format(
                        "INSERT INTO Objets (Nom, Type, Categorie, Adresse_IP, Location, Etat) VALUES ('%s', '%s', '%s', '%s', '%s', %d)",
                        nom, type, categorie, adresseIp, location, etat);
                statement.executeUpdate(insertObjet);
            }
        }
    }

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
                int objetID = random.nextInt(10) + 1; // Génère un objet ID aléatoire entre 1 et 5

                String insertCapteur = String.format(
                        "INSERT INTO Capteurs (Nom, Modele, Numero_serie, Etat, Objet_ID) VALUES ('%s', '%s', '%s', %d, %d)",
                        nom, modele, numeroSerie, etat, objetID);

                statement.executeUpdate(insertCapteur);
            }
        }
    }

    // Méthode pour insérer des données aléatoires de capteurs dans la table
    // "DonneesCapteurs"
    public void insertRandomCapteursData(Connection connection) throws SQLException {
        Random random = new Random();
        String insertDataQuery = "INSERT INTO DonneesCapteurs (Valeur, Timestamp, Capteur_ID) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery)) {
            // Générer des données pour 10 capteurs
            for (int capteurId = 1; capteurId <= 10; capteurId++) {
                // Générer des données pour 10 mesures par capteur
                for (int i = 1; i <= 10; i++) {
                    float valeur = random.nextFloat() * 100; // Valeur de mesure aléatoire entre 0 et 100
                    Timestamp timestamp = generateRandomTimestamp(); // Générer un timestamp aléatoire

                    // Définir les valeurs des paramètres dans la requête préparée
                    preparedStatement.setFloat(1, valeur);
                    preparedStatement.setTimestamp(2, timestamp);
                    preparedStatement.setInt(3, capteurId);

                    // Exécuter la requête préparée pour insérer les données
                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    // generer l here automoatiwque
    private Timestamp generateRandomTimestamp() {
        long offset = Timestamp.valueOf("2023-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2024-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long) (Math.random() * diff));
    }

    // Méthode pour obtenir un type aléatoire
    private String getRandomType() {
        String[] types = { "Type1", "Type2", "Type3", "Type4", "Type5" };
        Random random = new Random();
        return types[random.nextInt(types.length)];
    }

    // Méthode pour obtenir une catégorie aléatoire
    private String getRandomCategorie() {
        String[] categories = { "Categorie1", "Categorie2", "Categorie3", "Categorie4", "Categorie5" };
        Random random = new Random();
        return categories[random.nextInt(categories.length)];
    }

    // Méthode pour obtenir un emplacement aléatoire
    private String getRandomLocation() {
        String[] locations = { "Location1", "Location2", "Location3", "Location4", "Location5" };
        Random random = new Random();
        return locations[random.nextInt(locations.length)];
    }

    // Vérifie si les données à insérer dans la colonne Objet_ID de la table
    // Capteurs existent dans la colonne ID de la table Objets
    public static boolean verifieObjetID(Connection connection, int objetID) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT ID FROM Objets");
            Set<Integer> objetsIDs = new HashSet<>();
            while (resultSet.next()) {
                objetsIDs.add(resultSet.getInt("ID"));
            }
            return objetsIDs.contains(objetID);
        }
    }

    public static boolean verifieRequeteInsertion(Connection connection, String insertQuery) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Exécute la requête pour obtenir le nombre de colonnes spécifié
            ResultSet resultSet = statement.executeQuery(insertQuery);
            int columnCount = resultSet.getMetaData().getColumnCount();

            return columnCount == 6;
        }
    }

    // Méthode pour obtenir une adresse IP aléatoire (sous forme de chaîne simple
    // pour l'exemple)
    private String getRandomIpAddress() {
        Random random = new Random();
        return String.format("%d.%d.%d.%d", random.nextInt(256), random.nextInt(256), random.nextInt(256),
                random.nextInt(256));
    }
}