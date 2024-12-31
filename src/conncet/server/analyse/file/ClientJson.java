package conncet.server.analyse.file;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientJson {
    public static void main(String[] args) {
        try {
            // Create the JSON data
            String jsonData = "{\"name\":\"John\",\"age\":30}";

            // Create a URL object
            URL url = new URL("http://localhost:6000/data");
            
            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Set the request method to POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Write the JSON data to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            
            // Check if the response code is 200 (OK)
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            // Read the response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response from server: " + response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
