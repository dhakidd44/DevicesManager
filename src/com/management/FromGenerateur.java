package com.management;

import java.sql.*;
import java.util.Random;
import java.util.Stack;
import java.util.Queue;
//import java.util.LinkedList;

public class FromGenerateur {
    private Random random;
    private Connection connection;

    public FromGenerateur(Connection connection) {
        this.random = new Random();
        this.connection = connection;
    }

    // Méthode pour générer et ajouter des données aléatoires à une pile
    public void addRandomDataToStack(Stack<Data> stack, int numberOfData) {
        for (int i = 0; i < numberOfData; i++) {
            float valeur = random.nextFloat() * 100; // Valeur de mesure aléatoire entre 0 et 100
            Timestamp timestamp = timeStampAleatoirTimestamp(); // Générer un timestamp aléatoire
            stack.push(new Data(valeur, timestamp));
        }
    }

    // Méthode pour insérer des données de capteurs depuis la base de données à une file
    public void ajouterDesDonneesDansQueue(Queue<Data> queue, int numberOfData) throws SQLException {
        String selectQuery = "SELECT Valeur, Timestamp FROM DonneesCapteurs LIMIT ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, numberOfData);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                float valeur = resultSet.getFloat("Valeur");
                Timestamp timestamp = resultSet.getTimestamp("Timestamp");
                queue.offer(new Data(valeur, timestamp));
            }
        }
    }

    // Méthode pour générer un timestamp aléatoire
    private Timestamp timeStampAleatoirTimestamp() {
        long offset = Timestamp.valueOf("2020-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2024-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long)(Math.random() * diff));
    }

    // Classe pour représenter les données qu on va simuler
    public static class Data {
        private float valeur;
        private Timestamp timestamp;

        public Data(float valeur, Timestamp timestamp) {
            this.valeur = valeur;
            this.timestamp = timestamp;
        }

        public float getValue() {
            return valeur;
        }

        public Timestamp getTimestamp() {
            return timestamp;
        }
    }
}
