import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

// TODO : Should delete this , the request will just from the server, not from the user client . 
public class AIClient 
{
    public static void main(String[] args) 
    {
        try 
        {
	        	
	            URL url = new URL("http://localhost:3000/api/predict");
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type", "application/json; utf-8");
	            conn.setRequestProperty("Accept", "application/json");
	            conn.setDoOutput(true);
	
	            // JSON body
	            String jsonInput = "{ \"input\": \"Hello from Java client\" }";
	
	            try(OutputStream os = conn.getOutputStream()) 
	            {
	                byte[] input = jsonInput.getBytes("utf-8");
	                os.write(input, 0, input.length);
	            }
	
	            // Read response
	            try(BufferedReader br = new BufferedReader(
	                new InputStreamReader(conn.getInputStream(), "utf-8"))) 
	            {
	                StringBuilder response = new StringBuilder();
	                String responseLine;
	                while ((responseLine = br.readLine()) != null) {
	                    response.append(responseLine.trim());
	                }
	                System.out.println("AI Response: " + response.toString());
	            }
	
	            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
