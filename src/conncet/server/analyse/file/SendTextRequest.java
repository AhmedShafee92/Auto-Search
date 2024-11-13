package conncet.server.analyse.file;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendTextRequest {
    public static void main(String[] args) throws Exception {
        String textToAnalyze = "This is the text I want to analyze.";
        String urlString = "http://localhost:3000/analyze_text"; // Replace if server runs elsewhere

        try {
            // Create URL object
            URL url = new URL(urlString);

            // Open connection to server
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Set request method and content type
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Prepare JSON data (optional, adjust based on your server)
            String jsonData = "{\"text\": \"" + textToAnalyze + "\"}";

            // Send the POST request
            con.getOutputStream().write(jsonData.getBytes());
            con.getOutputStream().flush();

            // Check response code
            int responseCode = con.getResponseCode();

            // Process response if successful
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                System.out.println(content.toString());
            } else {
                System.out.println("Request failed.  HTTP Error code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
} 