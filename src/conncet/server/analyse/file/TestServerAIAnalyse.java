package conncet.server.analyse.file;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestServerAIAnalyse {
    public static void main(String[] args) throws Exception {
        String textToAnalyze = "What is the capital of france  .";
        String url = "http://localhost:3000/analyze_text"; // Replace with your server URL

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Set request method to POST
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        // Prepare JSON data
        String jsonData = "{\"text\": \"" + textToAnalyze + "\"}";

        // Send the POST request
        con.getOutputStream().write(jsonData.getBytes());
        con.getOutputStream().flush();

        // Read the response
        int responseCode = con.getResponseCode();
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
    }
}