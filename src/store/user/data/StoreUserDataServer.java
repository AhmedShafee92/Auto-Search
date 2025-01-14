package store.user.data;
import java.net.HttpURLConnection;
import java.net.URL;

public class StoreUserDataServer 
{

    public static void createAnalyseUserFiles() 
    {
        try {
            URL url = new URL("http://localhost:8000/storage_first_stage_analysing");

            // Open a connection to the server
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();            
            connection.setRequestMethod("GET");            
            int responseCode = connection.getResponseCode();
            
            // Print the response code to the console
            System.out.println("Response Code: " + responseCode);
            
            // If the response code is 200 (OK), the server will execute the process
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Request sent successfully to the server.");
            } else {
                System.out.println("Request failed. Response Code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void reateSensitiveUserFiles() 
    {
	
    	// create the sensitive file in the cloud and store the sensitive data into the file 
    	// send post method with encrypted data inside the JSON file 
    	
    	
    	
    	

	}    
    
    
    
	
}







