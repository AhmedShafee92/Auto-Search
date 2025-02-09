package conncet.server.analyse.file;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.nio.charset.StandardCharsets;

public class ConnectConvertStringToJson 
{
	private static String fileTotext = "";
	private static String fileLocation = "AppStorage/personal_data/user_cv.docx";
	private static String jsonFileLocation = "AppStorage/analyse_data/user_analyse_data.json";
	
	public static void uploadJsonFileCloud() 
	{
		
        try {
        	   
        		FileReader fileReader = new FileReader(jsonFileLocation);            
               // Use JsonParser to parse the file into a JsonObject
               JsonObject jsonObject = JsonParser.parseReader(fileReader).getAsJsonObject();      
               String jsonInputString = jsonObject.toString();
                 
	            String serverUrl = "http://localhost:8000/upload-json";
	            // Create a URL object and open a connection
	            URL url = new URL(serverUrl);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	
	            // Configure the connection for POST
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-Type", "application/json");
	            connection.setDoOutput(true);
	
	            // Send the JSON data
	            try (OutputStream os = connection.getOutputStream()) 
	            {
	                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
	                os.write(input, 0, input.length);
	            }

	            // Read the response from the server
	            int status = connection.getResponseCode();
	            if (status == HttpURLConnection.HTTP_OK) {
				
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    String responseLine;
                    StringBuilder response = new StringBuilder();
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Server response: " + response.toString());
                }
            } else {
                System.out.println("Server responded with status code: " + status);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		
    }
	

	public static boolean serverConvertWordToJson() throws IOException 
	{
		convetFileToText(fileLocation); 	    
	    String fileString = sanitizeString(fileTotext);
	    StringBuilder sb = serverConvertWordToJson(fileString);	    
	    String jsonString = sb.toString();
        // Parse the JSON string to a JsonObject using Gson
	    // JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        // check that the string valid JSON format object 
        if (isValidJson(jsonString)) 
        {
            // write the string to JSON file 
            JsonElement jsonElement = JsonParser.parseString(jsonString);
            // Check if the parsed element is a JSON Object
            if (jsonElement.isJsonObject()) 
            {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                try {
	                    // Write the JSON to a file using Gson
	                   // Gson gson = new Gson();
	                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	                    FileWriter writer = new FileWriter(jsonFileLocation);	                    
	                    gson.toJson(jsonObject, writer);  
	                    writer.close();	                    
                    } catch (IOException e) 
                	{
                    	e.printStackTrace();
                	}
               
            }else 
            {
                System.out.println("NOT a valid JSON Object.");
        		return false; 	

            }
            
        }else 
        {
            System.out.println("The string is NOT a valid JSON.");
    		return false; 	

    	}

		return true; 	
	}
	
		
    public static StringBuilder  positionsListForUser(String inputString) 
    {
        String serverUrl = "http://localhost:4000/process";
        StringBuilder response = new StringBuilder();
        try {
       
	            // Step 1: Create a URL object and open a connection
	            URL url = new URL(serverUrl);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	
	            // Step 2: Configure the connection for POST
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-Type", "application/json");
	            connection.setDoOutput(true);
	
	            // Step 3: Create the JSON request body
	            String jsonInputString = "{ \"inputString\": \"" + inputString + "\" }";
	
	            // Step 4: Send the JSON data
	            try (OutputStream os = connection.getOutputStream()) 
	            {
	                byte[] input = jsonInputString.getBytes("utf-8");
	                os.write(input, 0, input.length);
	                System.out.println("success");
	            }
	
	            // Step 5: Read the JSON response
	            int status = connection.getResponseCode();
	            if (status == HttpURLConnection.HTTP_OK) 
	            {
	               
	            	try (BufferedReader br = new BufferedReader(
	                        new InputStreamReader(connection.getInputStream(), "utf-8"))) 
	            	{
	                    String responseLine;
	                    while ((responseLine = br.readLine()) != null) 
	                    {
	                        response.append(responseLine.trim());
	                    }
	
	                }	
	                       
	            } 
	            else 
            	{
            		System.out.println("Server responded with status code: " + status);
            		return null;
            	}

        } catch (IOException e) 
        {
            e.printStackTrace();
            return null ; 
        }
        return response; 
    }

    
    
    public static StringBuilder  placesListForUser(String inputString) 
    {
        String serverUrl = "http://localhost:4000/process";
        StringBuilder response = new StringBuilder();
        try {
       
	            // Step 1: Create a URL object and open a connection
	            URL url = new URL(serverUrl);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	
	            // Step 2: Configure the connection for POST
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-Type", "application/json");
	            connection.setDoOutput(true);
	
	            // Step 3: Create the JSON request body
	            String jsonInputString = "{ \"inputString\": \"" + inputString + "\" }";
	
	            // Step 4: Send the JSON data
	            try (OutputStream os = connection.getOutputStream()) 
	            {
	                byte[] input = jsonInputString.getBytes("utf-8");
	                os.write(input, 0, input.length);
	                System.out.println("success");
	            }
	
	            // Step 5: Read the JSON response
	            int status = connection.getResponseCode();
	            if (status == HttpURLConnection.HTTP_OK) 
	            {
	               
	            	try (BufferedReader br = new BufferedReader(
	                        new InputStreamReader(connection.getInputStream(), "utf-8"))) 
	            	{
	                    String responseLine;
	                    while ((responseLine = br.readLine()) != null) 
	                    {
	                        response.append(responseLine.trim());
	                    }
	
	                }	
	                       
	            } 
	            else 
            	{
            		System.out.println("Server responded with status code: " + status);
            		return null;
            	}

        } catch (IOException e) 
        {
            e.printStackTrace();
            return null ; 
        }
        return response; 
    }
    
    
    
    // Method to save JSON string to a local file
    @SuppressWarnings("unused")
	private static void saveJsonToFile(String jsonString, String fileName) throws IOException 
    {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonString);
        }
        
    }

	public static StringBuilder serverConvertWordToJson(String inputString) 
    {

    	  String serverUrl = "http://localhost:4000/FileCVToJson";
          StringBuilder response = new StringBuilder();
          try {
  	            // Step 1: Create a URL object and open a connection
  	            URL url = new URL(serverUrl);
  	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	
  	            // Step 2: Configure the connection for POST
  	            connection.setRequestMethod("POST");
  	            connection.setRequestProperty("Content-Type", "application/json");
  	            connection.setDoOutput(true);
  	
  	            // Step 3: Create the JSON request body
  	            String jsonInputString = "{ \"inputString\": \"" + inputString + "\" }";
  	
  	            // Step 4: Send the JSON data
  	            try (OutputStream os = connection.getOutputStream()) 
  	            {
  	                byte[] input = jsonInputString.getBytes("utf-8");
  	                os.write(input, 0, input.length);
  	                System.out.println("success");
  	            }
  	
  	            // Step 5: Read the JSON response
  	            int status = connection.getResponseCode();
  	            if (status == HttpURLConnection.HTTP_OK) 
  	            {
  	               
  	            	try (BufferedReader br = new BufferedReader(
  	                        new InputStreamReader(connection.getInputStream(), "utf-8"))) 
  	            	{
  	                    String responseLine;
  	                    while ((responseLine = br.readLine()) != null) 
  	                    {
  	                        response.append(responseLine.trim());
  	                    }
  	
  	                }	
  	                       
  	            } 
  	            else 
              	{
              		System.out.println("Server responded with status code: " + status);
              		return null;
              	}

          } catch (IOException e) 
          {
              e.printStackTrace();
              return null ; 
          }
          return response;   

	}
    
    
	public static String convetFileToText(String fileLocationLocal) throws IOException 
	{
		if(fileLocationLocal == null)
		{
			System.err.println("your path of file is null ");
			return null;
		}
		// Create a File object for the input file . 
		try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(fileLocationLocal)))) 
		 {
		
		    XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
		    String docText = xwpfWordExtractor.getText();
		    xwpfWordExtractor.close();
		    fileTotext += docText;
		    return docText;	            
		    
		 }
	
	 }

	  public static String sanitizeString(String inputString) {
	        if (inputString == null) {
	            return null;
	        }
	        // Remove invalid control characters
	        return inputString.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F]", "");
	    }
	  
	  public static boolean isValidJson(String json) {
	        try {
	            // Try to parse the string as JSON
	            JsonParser.parseString(json);
	            return true; // If parsing succeeds, it's valid JSON
	        } catch (JsonSyntaxException e) {
	            return false; // If parsing fails, it's invalid JSON
	        }
	    }
	  
	
}

