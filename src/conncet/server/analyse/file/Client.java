package conncet.server.analyse.file;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {

    public static void main(String[] args) {
        try {
            // URL of the Node.js server
            String url = "http://localhost:3000";

            // Create a URL object
            URL obj = new URL(url);
            
            // Open connection to the server
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
            // Set the request method to GET
            con.setRequestMethod("GET");
            
            // Get the response code
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            // Read the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            // Print the response from the server
            System.out.println("Response: " + response.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
