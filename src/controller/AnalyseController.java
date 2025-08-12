package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import javax.swing.JOptionPane;

public class AnalyseController 
{
    
	// TODO : should delete this function , unused . 
    public static void sendAnalyseRequest(int userId) 
    {
        try {
	            URL url = new URL("http://localhost:3000/api/users/" + userId + "/analyse-insert");
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type", "application/json");
	            conn.setDoOutput(true); // Required for POST, even if body is empty
	
	            // You can send an empty JSON object if needed
	            String body = "{}";
	            conn.getOutputStream().write(body.getBytes("UTF-8"));
	
	            // Read the response
	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            StringBuilder response = new StringBuilder();
	            String line;
	            while ((line = in.readLine()) != null) {
	                response.append(line);
            }
            in.close();

            // Show success message (or handle response)
            JOptionPane.showMessageDialog(null, "Analyse complete!\nResponse: " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
  
    
    public static String sendAnalyseRequest(int userId, File file) throws IOException 
    {
        String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
        String LINE_FEED = "\r\n";

        URL url = new URL("http://localhost:3000/api/analyse/" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (OutputStream output = connection.getOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"), true)) 
        {

            // Send the file
            String fileName = file.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: " + Files.probeContentType(file.toPath())).append(LINE_FEED);
            writer.append(LINE_FEED).flush();

            Files.copy(file.toPath(), output);
            output.flush();
            writer.append(LINE_FEED).flush();

            // End of multipart/form-data
            writer.append("--" + boundary + "--").append(LINE_FEED).flush();
        }

        int responseCode = connection.getResponseCode();
        InputStream responseStream = (responseCode == HttpURLConnection.HTTP_OK)
                ? connection.getInputStream()
                : connection.getErrorStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(responseStream))) 
        {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }   
    
}