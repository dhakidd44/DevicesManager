/*
  Nom du fichier: Traitement.java
  Titre      : Traitement des opérations sur les objets.(_Devices Manager)
  Auteur     : Ernest Samuel Andre
  Date       : 18/01/2024
  Description: Cette classe gère les opérations liées aux objets, telles que l'insertion, la mise à jour,
               la suppression et l'affichage des données. Elle communique avec la base de données PostgreSQL.
  Version    : 1.0.0
*/

package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Traitement {
    private static final Scanner scanner = new Scanner(System.in);
    Validation Validation = new Validation();

    private Connection connection;

    public Traitement(Connection connection) {
        this.connection = connection;
    }

    public void insertAppareilFromUserInput() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Saisissez les informations pour le nouvel Objet :");

     //   System.out.print("Nom : ");
        String nom = Validation.validateStringInput("Nom : ", "[a-zA-Z ]+");

      //  System.out.print("Type : ");
        String type = Validation.validateStringInput("Type : ", "[a-zA-Z]+");

      //  System.out.print("Catégorie : ");
        String categorie = Validation.validateStringInput("Catégorie : ", "[a-zA-Z]+");

      //  System.out.print("Location : ");
        String location = Validation.validateStringInput("Location : ", "[a-zA-Z]+");
        

        insertAppareil(nom, type, categorie, location);

        // Menu pour ajouter des capteurs ou des actionneurs
        int choix;
        do {
            System.out.println("Menu:");
            System.out.println("1. Ajouter un capteur");
            System.out.println("2. Ajouter un actionneur");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choix : ");

            try {
                while (!scanner.hasNextLine()) {
                    System.out.println("Attente de l'entrée de l'utilisateur...");
                    scanner.next();
                }

                String input = scanner.nextLine();
                System.out.println("Entrée utilisateur : " + input);

                choix = Integer.parseInt(input.trim());

                switch (choix) {
                    case 1:
                        // Ajouter un capteur
                        insertCapteurFromUserInput();
                        break;
                    case 2:
                        // Ajouter un actionneur
                        insertActuateurFromUserInput();
                        break;
                    case 0:
                        // Retour au menu principal
                        System.out.println("Retour au menu principal...");
                        break;
                    default:
                        System.out.println("Choix invalide. Réessayez.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Réessayez.");
                choix = -1; // Mettre une valeur qui ne correspond à aucun cas pour forcer la répétition
            }
        } while (choix != 0);
        scanner.close();
    }

    // Fonctiions pour afficher les objets les types de capteurs et d actuateurs que le systeme
    // supporte
// Méthode pour afficher tous les objets en utilisant la connexion spécifiée
public void afficherTousObjets(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
        String selectQuery = "SELECT ID, Nom FROM Objets";
        ResultSet resultSet = statement.executeQuery(selectQuery);

        System.out.println("=======================================");
        System.out.println("| ID\t| Nom");
        System.out.println("=======================================");
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String nom = resultSet.getString("Nom");

            System.out.println("| " + id + "\t| " + nom);
        }
        System.out.println("=======================================");
    }
}
    public void afficherTousCapteurs(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String selectQuery = "SELECT ID, nom_type FROM TypeCapteur";
            ResultSet resultSet = statement.executeQuery(selectQuery);
    
            System.out.println("=======================================");
            System.out.println("| ID\t| Nom");
            System.out.println("=======================================");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nom = resultSet.getString("nom_type");
    
                System.out.println("| " + id + "\t| " + nom);
            }
            System.out.println("=======================================");
        }
    }
    public void afficherTousActuateurs(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String selectQuery = "SELECT ID, nom_type FROM TypeActuateur";
            ResultSet resultSet = statement.executeQuery(selectQuery);
    
            System.out.println("=======================================");
            System.out.println("| ID\t| nom_type");
            System.out.println("=======================================");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nom = resultSet.getString("nom_type");
    
                System.out.println("| " + id + "\t| " + nom);
            }
            System.out.println("=======================================");
        }
    }
    
    

    public void afficherTypesActuateurs(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String selectQuery = "SELECT * FROM TypeActuateur";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            System.out.println("Liste des modeles d'actionneurs :");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nomType = resultSet.getString("Nom_Type");
                System.out.println("ID : " + id + ", Nom du type : " + nomType);
            }
        }
    }

    // Demande a l utilisateur d apparail quil veut mettre jaour
    public void newDemand() throws SQLException {
        System.out.print("Entrez l'ID de l'appareil que vous souhaitez mettre à jour : ");
        int appareilIdToUpdate = scanner.nextInt();

        System.out.print("Entrez le nouvel état de fonctionnement (1 pour actif, 0 pour inactif) : ");
        int newStateToUpdate = scanner.nextInt();
        updateAppareilState(appareilIdToUpdate, newStateToUpdate);

    }

    private String validateStringInput(Scanner scanner) {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("La saisie ne peut pas être vide. Réessayez.");
            }
        } while (input.isEmpty());
        return input;
    }

    private void insertAppareil(String nom, String type, String categorie, String location)
            throws SQLException {
        try {
            // Exécution de la requête d'insertion
            String insertQuery = "INSERT INTO Objets (Nom, Type, Categorie, location) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, type);
                preparedStatement.setString(3, categorie);
                preparedStatement.setString(4, location);

                // Exécution de la requête d'insertion
                preparedStatement.executeUpdate();

                System.out.println("Appareil inséré avec succès.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion d'un appareil : " + e.getMessage());
        }
    }

    // Insertion des informations des actuateurs par l utilisateur
    public void insertCapteurFromUserInput() throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Saisissez les informations pour le nouveau Capteur :");

      //  System.out.print("Nom : ");
        String nom = Validation.validateStringInput("Nom : ", "[a-zA-Z ]+");

      //  System.out.print("Modèle : ");
        String modele = Validation.validateStringInput("Modele : ", "[a-zA-Z ]+");

    //    System.out.print("Numéro de série : ");
        String numeroSerie = validateStringInput(scanner);

        System.out.print("État (1 pour actif, 0 pour inactif) : ");
        int etat = scanner.nextInt();

        try {
            afficherTousObjets(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Choisissez l'id de l'objet auquel le capteur appartient: ");
        int objet_id = scanner.nextInt();

        try {
            afficherTousCapteurs(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Choisissez l'id du type de auquel le capteur appartient");
        int typeCapteur_ID = scanner.nextInt();


        insertCapteur(nom, modele, numeroSerie, etat,objet_id, typeCapteur_ID);
    }

    private void insertCapteur(String nom, String modele, String numeroSerie, int etat,int objet_id, int typeCapteur_ID) throws SQLException {
        try {
            String insertQuery = "INSERT INTO Capteurs (Nom, Modele, Numero_serie, Etat, Objet_id, TypeCapteur_ID) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, modele);
                preparedStatement.setString(3, numeroSerie);
                preparedStatement.setInt(4, etat);
                preparedStatement.setInt(5, objet_id);
                preparedStatement.setInt(6, typeCapteur_ID);

                preparedStatement.executeUpdate();

                System.out.println("Capteur inséré avec succès.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion d'un capteur : " + e.getMessage());
        }
    }

    // Insertion des informations des actuateurs par l utilisateur
    public void insertActuateurFromUserInput() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Saisissez les informations pour le nouvel Actuateur :");

        System.out.print("Nom : ");
        String nom = validateStringInput(scanner);

        System.out.print("Modèle : ");
        String modele = validateStringInput(scanner);

        System.out.print("Numéro de série : ");
        String numeroSerie = validateStringInput(scanner);

        System.out.print("État (1 pour actif, 0 pour inactif) : ");
        int etat = scanner.nextInt();


        try {
            afficherTousObjets(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Choisissez l'id de l'objet auquel le capteur appartient: ");
        int objet_id = scanner.nextInt();

        try {
            afficherTousActuateurs(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Choisissez l'id du type de auquel l'actuateur appartient");
        int typeActuateur_ID = scanner.nextInt();

        insertActuateur(nom, modele, numeroSerie, etat, objet_id, typeActuateur_ID);
    }

    private void insertActuateur(String nom, String modele, String numeroSerie, int objet_id, int etat, int typeActuateur_ID) throws SQLException {
        try {
            String insertQuery = "INSERT INTO Actuateurs (Nom, Modele, Numero_serie, Etat) VALUES (?, ?, ?, ?, ?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, modele);
                preparedStatement.setString(3, numeroSerie);
                preparedStatement.setInt(4, etat);
                preparedStatement.setInt(5, objet_id);
                preparedStatement.setInt(6, typeActuateur_ID);

                preparedStatement.executeUpdate();

                System.out.println("Actuateur inséré avec succès.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion d'un actuateur : " + e.getMessage());
        }
    }

    public List<ObjetConnecte> getAllAppareils() throws SQLException {
        List<ObjetConnecte> appareilList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Objets";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery)) {
            while (resultSet.next()) {
                ObjetConnecte appareil = new ObjetConnecte(
                        resultSet.getInt("ID"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Type"),
                        resultSet.getString("Categorie"),
                        resultSet.getString("Location"),
                        resultSet.getInt("Etat")

                );
                appareilList.add(appareil);
            }
        }
        return appareilList;
    }

    public void updateAppareilState(int appareilId, int newState) {
        try {
            String query = "UPDATE objets SET etat = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, newState);
                preparedStatement.setInt(2, appareilId);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("État de l'appareil mis à jour avec succès.");
                } else {
                    System.out.println("Aucun appareil trouvé avec l'ID fourni.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int demanderIdAppareil() {
        int appareilId;
        do {
            System.out.print("Entrez l'ID de l'appareil : ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrée invalide. Veuillez entrer un entier.");
                scanner.next(); // Effacer l'entrée invalide
            }
            appareilId = scanner.nextInt();
            if (appareilId < 0) {
                System.out.println("L'ID de l'appareil ne peut pas être négatif. Réessayez.");
            }
        } while (appareilId < 0);
        return appareilId;
    }

    public static int demanderNouvelEtat() {
        int nouvelEtat;
        do {
            System.out.print("Entrez le nouvel état de fonctionnement (1 pour actif, 0 pour inactif) : ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrée invalide. Veuillez entrer un entier.");
                scanner.next(); // Effacer l'entrée invalide
            }
            nouvelEtat = scanner.nextInt();
            if (nouvelEtat != 0 && nouvelEtat != 1) {
                System.out.println("Le nouvel état doit être soit 0 soit 1. Réessayez.");
            }
        } while (nouvelEtat != 0 && nouvelEtat != 1);
        return nouvelEtat;
    }

    public static void fermerScanner() {
        scanner.close();
    }

    // Méthode pour supprimer un appareil par ID
    public void deleteAppareilById() throws SQLException {
        // Utilisation de Scanner pour lire l'ID à partir de l'entrée utilisateur
        System.out.print("Entrez l'ID de l'appareil que vous souhaitez supprimer : ");

        int appareilIdToDelete = scanner.nextInt();

        // Vérifier si l'ID existe avant de supprimer
        if (appareilExists(appareilIdToDelete)) {

            String deleteAppareilQuery = "DELETE FROM Objets WHERE ID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteAppareilQuery)) {
                preparedStatement.setInt(1, appareilIdToDelete);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Appareil supprimé avec succès.");
                } else {
                    System.out.println("Une erreur s'est produite lors de la suppression de l'appareil.");
                }
            }
        } else {
            System.out.println("Aucun appareil trouvé avec cet ID.");
        }
    }

    private boolean appareilExists(int appareilId) throws SQLException {
        String checkAppareilQuery = "SELECT COUNT(*) FROM Objets WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkAppareilQuery)) {
            preparedStatement.setInt(1, appareilId);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}
