import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestServerConnection {

    public static void main(String[] args) {
    	String jsonInput ="hiii this is the client , we tset the connection with the srever ";
    	String urlString = "http://localhost:3000/analyse_text";    	
    	String jsonInputString = "{\"text\": \"" + jsonInput + "\"}";
    	
        try {

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Request setup
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // Send JSON body
            try (OutputStream os = conn.getOutputStream()) 
            {
                byte[] input = jsonInputString.getBytes("utf-8");    
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
