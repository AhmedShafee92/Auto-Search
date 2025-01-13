package store.user.data;

import java.net.HttpURLConnection;
import java.net.URL;

public class CreateFoldersCloud 
{

	//TODO :should add the options folders that have the search engines 
	public static boolean createFoldersCloud() 
	{
		// TODO Auto-generated method stub
		if (createPersonalUserData() &&
		createAnalyseUserData() &&
		createPrivacyUserData() )
		{
			// Success to create the folders 
			return true;
		}
		
		// success to create all the folders 
		return false ;
		
	}
	
	private static boolean createPersonalUserData() 
	{
		
        try {
            @SuppressWarnings("deprecation")
			URL url = new URL("http://localhost:8000/create-personal-user-data");

            // Open a connection to the server
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();            
            connection.setRequestMethod("GET");            
            int responseCode = connection.getResponseCode();
      
            // If the response code is 200 (OK), the server will execute the process
            if (responseCode == HttpURLConnection.HTTP_OK) {
            	// success do nothing 
            } else 
            {
            	// Print the response code to the console
            	System.out.println("Response Code: " + responseCode);
                System.out.println("Request failed. Response Code: " + responseCode);
                return false ;
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return false ;
        }

		return true; 
	}
	
	
	private static boolean createAnalyseUserData() 
	{

        try {
            @SuppressWarnings("deprecation")
			URL url = new URL("http://localhost:8000/create-analyse-user-data");

            // Open a connection to the server
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();            
            connection.setRequestMethod("GET");            
            int responseCode = connection.getResponseCode();
      
            // If the response code is 200 (OK), the server will execute the process
            if (responseCode == HttpURLConnection.HTTP_OK) {
            	// success do nothing 
            } else 
            {
            	// Print the response code to the console
            	System.out.println("Response Code: " + responseCode);
                System.out.println("Request failed. Response Code: " + responseCode);
                return false ;
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return false ;
        }

		return true; 		
		
	}
	
	
	private static boolean createPrivacyUserData() 
	{

        try{
	            @SuppressWarnings("deprecation")
				URL url = new URL("http://localhost:8000/create-privacy-user-data");
	
	            // Open a connection to the server
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();            
	            connection.setRequestMethod("GET");            
	            int responseCode = connection.getResponseCode();
	      
	            // If the response code is 200 (OK), the server will execute the process
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	            	// success do nothing 
	            } else 
	            {
	            	// Print the response code to the console
	            	System.out.println("Response Code: " + responseCode);
	                System.out.println("Request failed. Response Code: " + responseCode);
	                return false ;
	            }

	        // Close the connection
	        connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return false ;
        }

		return true;
		
	}
	
	
}
