package conncet.server.analyse.file;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestConnectionServer {
    public static void main(String[] args) {
        // Server URL
        String serverUrl = "http://localhost:4000/";

        // Text to send to the server
        String textToAnalyze = "what is the capital of UK ";

        try {
            // Create a JSON request body
            String jsonInputString = "{\"text\": \"" + textToAnalyze + "\"}";

            // Open a connection to the server
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configure the connection
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Send the JSON data
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read the response from the server
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    // Output the server's response
                    System.out.println("Server Response: " + response.toString());
                }
            } else {
                System.out.println("Error: Server returned HTTP status code " + statusCode);
            }

        } catch (IOException e) {
            System.err.println("Error communicating with the server: " + e.getMessage());
        }
    }
}
