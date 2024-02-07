/*
  Titre      : Réalisation d'une application de gestion d'appareils avec une base de données PostgreSQL.(_Devices Manager)
  Auteur     : Ernest Samuel Andre
  Date       : 07/02/2024
  Description: Ce programme a pour but de créer une application de gestion d'appareils, utilisant une base de données PostgreSQL
               pour stocker les informations. L'application permet d'ajouter des appareils, de les afficher, de mettre à jour leur état,
               et de supprimer des appareils de la base de données.
  Description Classe: Cette classe est le point d'entrée de l'application. Elle permet à l'utilisateur
               d'interagir avec la base de données PostgreSQL pour gérer les informations sur les appareils.
  Version    : 1.0.0
*/

import com.management.Traitement;
import com.management.ObjetConnecte;
import com.management.CreateDb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            // Informations de la connexion pour PostgreSQL
            String url = "jdbc:postgresql://localhost:5432/new1";
            String utilisateur = "postgres";
            String motDePasse = "admin";

            // Établir la connexion
            Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);

            // Créer les tables en utilisant la classe CreateDb
            CreateDb createDb = new CreateDb();
            createDb.createObjetsTable(connection);
            createDb.createTypeCapteursTable(connection);
            createDb.createCapteursTable(connection);
            createDb.createDonneesCapteursTable(connection);
            createDb.createTypeActuateurTable(connection);
            createDb.createActuateursTable(connection);
            createDb.createDonneesActuateursTable(connection);

            // Operation de traitement
            Traitement traitement = new Traitement(connection);
            // Menu d'interaction avec l'utilisateur
            Scanner scanner = new Scanner(System.in);
            int choix;

            do {
                System.out.println("Menu:");
                System.out.println("1. Enregistrer des Appareils dans notre Répertoire");
                System.out.println("2. Afficher toutes les données");
                System.out.println("3. Mise à jour de l'État d'un Appareil");
                System.out.println("4. Supprimer un Appareil");
                System.out.println("0. Quitter");
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
                            traitement.insertAppareilFromUserInput();
                            break;
                        case 2:
                            List<ObjetConnecte> allAppareils = traitement.getAllAppareils();
                            for (ObjetConnecte appareil : allAppareils) {
                                System.out.println(appareil);
                            }
                            break;
                        case 3:
                            System.out.println("Mise a jour de l'Etat d'un Appareil");
                            traitement.newDemand();
                            break;
                        case 4:
                            System.out.println("Supprimer un Appareil");
                            traitement.deleteAppareilById();
                            break;
                        case 0:
                            System.out.println("Fin du programme");
                            break;
                        default:
                            System.out.println("Choix invalide. Réessayez.");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide. Réessayez.");
                    choix = -1;
                    scanner.nextLine(); // Clear scanner buffer
                }

            } while (choix != 0);

            // Fermer la connexion
            connection.close();
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
