/*
  Nom du fichier: Validation.java
  Titre      :Valiation entree user.
  Auteur     : Ernest Samuel Andre
  Date       : 07/02/2024
  Description: S'asssurer du bon format et de la bonne entree des utulisateurs
  Version    : 1.0.0
*/

package com.management;

import java.util.Scanner;

public class Validation implements AutoCloseable {

    private final Scanner scanner;

    public Validation() {
        this.scanner = new Scanner(System.in);
    }

    public String validateStringInput(String message, String regex) {
        String input;
        do {
            System.out.print(message);
            input = scanner.nextLine().trim();
            if (!input.matches(regex)) {
                System.out.println("Entrée invalide. Réessayez.");
            }
        } while (!input.matches(regex));
        return input;
    }

    public int validateIntegerInput(String message) {
        int input;
        do {
            System.out.print(message);
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un entier valide.");
                scanner.next(); // Effacer l'entrée invalide
            }
            input = scanner.nextInt();
            if (input < 0) {
                System.out.println("La valeur ne peut pas être négative. Réessayez.");
            }
        } while (input < 0);
        return input;
    }

    @Override
    public void close() {
        scanner.close();
    }
}
