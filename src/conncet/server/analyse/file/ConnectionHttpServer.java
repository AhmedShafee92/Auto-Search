package conncet.server.analyse.file;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionHttpServer 
{
    public static void positionsList(String[] args)
    {
    	//String that show the JSON file format
        String payload = "{\"message\":\"Hello this is test http server connection, HTTP Server!\"}"; 

        try {	
	            // Create a connection to the server
	            @SuppressWarnings("deprecation")
				URL url = new URL("http://127.0.0.1:4000/test_http_server");
	
	            // Establish HTTP connection
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type", "application/json; utf-8");
	            conn.setRequestProperty("Accept", "application/json");
	            conn.setDoOutput(true);

	            // Write the JSON payload to the output stream
	            // here the we send the JOSN format to the server 
	            try (OutputStream os = conn.getOutputStream()) 
	            {
	                byte[] input = payload.getBytes("utf-8");
	                os.write(input, 0, input.length);
	            }

	            // Read the response
	            // here we got the JSON format response from the server 
	            int responseCode = conn.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) 
	            { // If the response is 200 OK
	                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
	                    StringBuilder response = new StringBuilder();
	                    String responseLine;
	                    while ((responseLine = br.readLine()) != null) {
	                        response.append(responseLine.trim());
	                    }
	                    System.out.println("Response from server: " + response.toString());
	                }
	            } else 
	            {
	                System.out.println("Server responded with code: " + responseCode);
	            }

        } catch (IOException e) 
        {
            System.err.println("Error while sending request: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
