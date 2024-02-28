/*
  Nom du fichier: CreateDb.java
  Titre      : Création de tables dans la base de données.
  Auteur     : Ernest Samuel Andre
  Date       : 18/01/2024
  Description: Cette classe contient des méthodes pour créer les tables nécessaires dans la base de données.
               Elle inclut également des méthodes privées pour supprimer les tables existantes.
  Version    : 1.0.0
*/

package com.management;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDb {
    // Méthode pour créer la table "Objets"
    public void createObjetsTable(Connection connection) throws SQLException {

        dropCapteursTableIfExists(connection);
        dropObjetsTableIfExists(connection);
        dropTypeCapteursTableIfExists(connection);
        dropActuateursTableIfExists(connection);
        dropDonneesCapteursTableIfExists(connection);
        dropTypeActuateurTableIfExists(connection);
        dropDonneesActuateursTableIfExists(connection);

        try (Statement statement = connection.createStatement()) {
            String createTableObjets = "CREATE TABLE Objets ("
                    + "ID SERIAL PRIMARY KEY,"
                    + "Nom VARCHAR(255) NOT NULL,"
                    + "Type VARCHAR(255),"
                    + "Categorie VARCHAR(255),"
                    + "adresse_ip VARCHAR(15),"
                    + "Location VARCHAR(255),"
                    + "Etat INT DEFAULT 1, "
                    + "CONSTRAINT check_etat CHECK (Etat IN (0, 1)),"
                    + "UNIQUE (ID))";
            statement.executeUpdate(createTableObjets);
        }
    }

    // Méthode pour créer la table "Capteurs"
    public void createCapteursTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableCapteurs = "CREATE TABLE Capteurs ("
                    + "ID SERIAL PRIMARY KEY,"
                    + "Nom VARCHAR(255) NOT NULL,"
                    + "Modele VARCHAR(255) NOT NULL,"
                    + "Numero_serie VARCHAR(255) NOT NULL,"
                    + "Etat INT DEFAULT 1, "
                    + "Objet_ID INT,"
                    + "TypeCapteur_ID INT,"
                    + "CONSTRAINT check_etat CHECK (Etat IN (0, 1)),"
                    + "FOREIGN KEY (Objet_ID) REFERENCES Objets(ID),"
                    + "FOREIGN KEY (TypeCapteur_ID) REFERENCES TypeCapteur(ID))";
            statement.executeUpdate(createTableCapteurs);
        }
    }

    // Méthode pour créer la table "TypeCapteur"
    public void createTypeCapteursTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Création de la table TypeCapteur
            String createTableTypeCapteur = "CREATE TABLE IF NOT EXISTS TypeCapteur ("
                    + "ID SERIAL PRIMARY KEY,"
                    + "Nom_Type VARCHAR(255) NOT NULL)";
            statement.executeUpdate(createTableTypeCapteur);

            // Insertion des types de capteurs s'ils n'existent pas déjà
            String insertTypesCapteurs = "INSERT INTO TypeCapteur (Nom_Type) SELECT * FROM (VALUES "
                    + "('Capteur de température'),"
                    + "('Capteur d''humidité'),"
                    + "('Capteur de luminosité'),"
                    + "('Capteur de mouvement'),"
                    + "('Capteur de pression'),"
                    + "('Capteur de gaz'),"
                    + "('Capteur de proximité')) AS t(Nom_Type) WHERE NOT EXISTS (SELECT 1 FROM TypeCapteur WHERE Nom_Type = t.Nom_Type)";
            statement.executeUpdate(insertTypesCapteurs);
        }
    }

    // Méthode pour créer la table "Donnees"
    public void createDonneesCapteursTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableDonnees = "CREATE TABLE DonneesCapteurs ("
                    + "ID SERIAL PRIMARY KEY,"
                    + "Valeur FLOAT,"
                    + "Timestamp TIMESTAMP,"
                    + "Capteur_ID INT,"
                    + "TypeCapteur_ID INT,"
                    + "FOREIGN KEY (Capteur_ID) REFERENCES Capteurs(ID),"
                    + "FOREIGN KEY (TypeCapteur_ID) REFERENCES TypeCapteur(ID))";
            statement.executeUpdate(createTableDonnees);
        }
    }

    public void createTypeActuateurTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Création de la table TypeActuateur
            String createTableTypeActuateur = "CREATE TABLE TypeActuateur ("
                    + "ID SERIAL PRIMARY KEY,"
                    + "Nom_Type VARCHAR(255) NOT NULL)";
            statement.executeUpdate(createTableTypeActuateur);

            // Insertion des types d'actionneurs
            String insertTypesActuateurs = "INSERT INTO TypeActuateur (Nom_Type) VALUES "
                    + "('Actionneur de lumière'),"
                    + "('Actionneur de chauffage'),"
                    + "('Actionneur de climatisation'),"
                    + "('Actionneur de volet')";
            statement.executeUpdate(insertTypesActuateurs);
        }
    }

    // Méthode pour créer la table "Actuateurs"
    public void createActuateursTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableActuateurs = "CREATE TABLE Actuateurs ("
                    + "ID SERIAL PRIMARY KEY,"
                    + "Nom VARCHAR(255) NOT NULL,"
                    + "Modele VARCHAR(255) NOT NULL,"
                    + "Numero_serie VARCHAR(255) NOT NULL,"
                    + "Etat INT DEFAULT 1, "
                    + "Objet_ID INT,"
                    + "CONSTRAINT check_etat CHECK (Etat IN (0, 1)),"
                    + "FOREIGN KEY (Objet_ID) REFERENCES Objets(ID))";
            statement.executeUpdate(createTableActuateurs);
        }
    }

    // Méthode pour créer la table "Donnees"
    public void createDonneesActuateursTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Création de la table DonneesActuateur
            String createTableDonnees = "CREATE TABLE IF NOT EXISTS DonneesActuateurs ("
                    + "ID SERIAL PRIMARY KEY,"
                    + "Valeur FLOAT,"
                    + "Timestamp TIMESTAMP,"
                    + "Actuateur_ID INT,"
                    + "TypeActuateur_ID INT,"
                    + "FOREIGN KEY (Actuateur_ID) REFERENCES Actuateurs(ID),"
                    + "FOREIGN KEY (TypeActuateur_ID) REFERENCES TypeActuateur(ID))";
            statement.executeUpdate(createTableDonnees);
        }
    }

    // Méthode pour supprimer la table "Objets" si elle existe
    private void dropObjetsTableIfExists(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String dropTableObjets = "DROP TABLE IF EXISTS Objets CASCADE";
            statement.executeUpdate(dropTableObjets);
        }
    }

    // Méthode pour supprimer la table "Capteurs" si elle existe
    private void dropCapteursTableIfExists(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String dropTableCapteurs = "DROP TABLE IF EXISTS Capteurs CASCADE";
            statement.executeUpdate(dropTableCapteurs);
        }
    }

    // Méthode pour supprimer la table "TypeCapteur" si elle existe
    private void dropTypeCapteursTableIfExists(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String dropTableTypeCapteur = "DROP TABLE IF EXISTS TypeCapteur CASCADE";
            statement.executeUpdate(dropTableTypeCapteur);
        }
    }

    // Méthode pour supprimer la table "TypeActuateur" si elle existe
    private void dropTypeActuateurTableIfExists(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String dropTableTypeCapteur = "DROP TABLE IF EXISTS TypeActuateur CASCADE";
            statement.executeUpdate(dropTableTypeCapteur);
        }
    }

    // Méthode pour supprimer la table "Actuateurs" si elle existe
    private void dropActuateursTableIfExists(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String dropTableActuateurs = "DROP TABLE IF EXISTS Actuateurs CASCADE";
            statement.executeUpdate(dropTableActuateurs);
        }
    }

    // Méthode pour supprimer la table "DonneesCapteurs" si elle existe
    private void dropDonneesCapteursTableIfExists(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String dropTableDonneesCapteurs = "DROP TABLE IF EXISTS DonneesCapteurs CASCADE";
            statement.executeUpdate(dropTableDonneesCapteurs);
        }
    }

    // Méthode pour supprimer la table "DonneesActuateurs" si elle existe
    private void dropDonneesActuateursTableIfExists(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String dropTableDonneesActuateurs = "DROP TABLE IF EXISTS DonneesActuateurs CASCADE";
            statement.executeUpdate(dropTableDonneesActuateurs);
        }
    }

}