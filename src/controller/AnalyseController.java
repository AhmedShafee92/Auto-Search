package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import util.HttpClientHelper;

public class AnalyseController 
{
    public static void main(String[] args) {
    	sendAnalyseRequest(3);
	}
    public static boolean sendAnalyseRequest() 
    {
     	String url = "http://localhost:3000/api/analyse-insert";
		String emptyString = new String() ;
        try 
        {
	      	
	    		try {
		    			String response = HttpClientHelper.sendPost(url, emptyString);
		    			System.out.println("Login response: " + response);	
		    			return response.toLowerCase().contains("success");
	    			} catch (Exception e) 
	    			{
	    				e.printStackTrace();
	    				return false;
	    			}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			return false;
        }
    }
    
    public static void sendAnalyseRequest(int userId) {
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

    
    
    
}