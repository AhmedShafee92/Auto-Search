package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientHelper 
{

    public static String sendPost(String urlString, String jsonInput) throws IOException 
    {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        
        try (OutputStream os = conn.getOutputStream()) 
        {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        } 

        int status = conn.getResponseCode();
        InputStream is = (status < 400) ? conn.getInputStream() : conn.getErrorStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"))) 
        {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null)
                response.append(responseLine.trim());
            	System.out.println(response.toString());
            	
            return response.toString();
        } 
     
    }
    
}
