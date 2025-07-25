import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestServerConnection {

    public static void main(String[] args) {
    	String username ="admin";
    	String password = "admin";
    	String urlString = "http://localhost:3000/api/login";    	
    	
  

    	String jsonInput = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";    	
        try {

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Request setup
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // Send JSON body
            try (OutputStream os = conn.getOutputStream()) 
            {
                byte[] input = jsonInput.getBytes("utf-8");    
                os.write(input, 0, input.length);
            }

            // Read response
            int status = conn.getResponseCode();
            InputStream is = (status < 400) ? conn.getInputStream() : conn.getErrorStream();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;

                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                System.out.println("✅ Response: " + response);
            }

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
