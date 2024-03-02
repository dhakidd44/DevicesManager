package com.management;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Stack;

public class SimpleHttpServer {

    private static Stack<String> dataStack = new Stack<>();

    public static void main(String[] args) throws IOException {
        int serverPort = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        System.out.println("Serveur démarré sur le port : " + serverPort);

        // Endpoint pour recevoir les données des microcontrôleurs
        server.createContext("/data", new DataHandler());

        server.setExecutor(null); 

        server.start();
    }

    static class DataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("POST")) {
                InputStream inputStream = exchange.getRequestBody();
                String data = convertInputStreamToString(inputStream);

                // Stocker les données dans la pile
                storeData(data);

                String response = "Données reçues et stockées avec succès!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.flush();
                outputStream.close();
            } else {
                String response = "Méthode non autorisée. Utiliser POST.";
                exchange.sendResponseHeaders(405, response.getBytes().length);
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }

        private String convertInputStreamToString(InputStream inputStream) throws IOException {
            StringBuilder result = new StringBuilder();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.append(new String(buffer, 0, length));
            }
            return result.toString();
        }
    }

    private static synchronized void storeData(String data) {
        dataStack.push(data);
        System.out.println("Données stockées : " + data);
    }
}
