package conncet.server.analyse.file;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectConvertStringToJson 
{
    public static void main(String[] args) {
        String serverUrl = "http://localhost:4000/process";
        String inputString = "Hello, Server!"; // String to send to the server

        try {
            // Step 1: Create a URL object and open a connection
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Step 2: Configure the connection for POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Step 3: Create the JSON request body
            String jsonInputString = "{ \"inputString\": \"" + inputString + "\" }";

            // Step 4: Send the JSON data
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Step 5: Read the JSON response
            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    // Step 6: Save the JSON response to a local file
                    saveJsonToFile(response.toString(), "personal_data/response.json");
                    System.out.println("Response saved to response.json");
                }
            } else {
                System.out.println("Server responded with status code: " + status);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save JSON string to a local file
    private static void saveJsonToFile(String jsonString, String fileName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonString);
        }
    }
}

