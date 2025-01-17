package first.option.forsendcv;
import java.net.HttpURLConnection;
import java.net.URL;

import search.SearchJob;

public class FirstSearchOpation implements SearchJob
{
	int[] result = null;
	String[] companiesEmail = null;
	
	@Override
	public void search() 
	{
	
        try {
	            URL url = new URL("http://localhost:4000/search-first-option");
	
	            // Open a connection to the server
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();            
	            connection.setRequestMethod("GET");            
	            int responseCode = connection.getResponseCode();
	            
	            // Print the response code to the console
	            System.out.println("Response Code: " + responseCode);
	            
	            // If the response code is 200 (OK), the server will execute the process
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                System.out.println("Start the first search option ");
	            } else {
	                System.out.println("Request failed. Response Code: " + responseCode);
	            }
	
	            // Close the connection
	            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	@Override
	public void stopSearch() 
	{

		try {
	            URL url = new URL("http://localhost:4000/stop-search-first-option");
	
	            // Open a connection to the server
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();            
	            connection.setRequestMethod("GET");            
	            int responseCode = connection.getResponseCode();
	            
	            // Print the response code to the console
	            System.out.println("Response Code: " + responseCode);
	            
	            // If the response code is 200 (OK), the server will execute the process
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                System.out.println("stoping the first search option ");
	            } else {
	                System.out.println("Request failed. Response Code: " + responseCode);
	            }
	
	            // Close the connection
	            connection.disconnect();
	    } catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
			
		
	}
	

}
