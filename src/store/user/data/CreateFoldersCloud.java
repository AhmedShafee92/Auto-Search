package store.user.data;

import java.net.HttpURLConnection;
import java.net.URL;

public class CreateFoldersCloud 
{
	public static void main(String[] args) {
		createFoldersCloud();
	}

	public static boolean createFoldersCloud() 
	{
		if (createUserDataStorageFolders())
		{
			// Success to create the folders 
			return true;
		}
		// success to create all the folders 
		System.err.println("failde to create the storage folders ");
		return false ;
		
	}
	
	private static boolean createUserDataStorageFolders() 
	{
		
        try {
	            @SuppressWarnings("deprecation")
				URL url = new URL("http://localhost:8000/create-user-data-folders");
	
	            // Open a connection to the server
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();            
	            connection.setRequestMethod("GET");            
	            int responseCode = connection.getResponseCode();
	      
	            // If the response code is 200 (OK), the server will execute the process
	            if (responseCode == HttpURLConnection.HTTP_OK) 
	            {
	            	// success do nothing 
	            } else 
	            {
	            	// If the response failed 	
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
